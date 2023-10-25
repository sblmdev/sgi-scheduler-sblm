package pe.gob.sblm.sgi.scheduler.serviceImpl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.sblm.api.alfresco.manager.CMISManager;
import pe.gob.sblm.api.alfresco.manager.CMISManagerBuilder;
import pe.gob.sblm.api.alfresco.manager.FileProperties;
import pe.gob.sblm.api.alfresco.repository.Repository;
import pe.gob.sblm.api.commons.utility.FechaUtil;
import pe.gob.sblm.sgi.scheduler.bean.ArchivoAdjuntoBean;
import pe.gob.sblm.sgi.scheduler.properties.PropiedadesAlfresco;
import pe.gob.sblm.sgi.scheduler.service.UploadRepositorioService;

@Transactional(readOnly = true)
@Service(value="uploadRepositorioService")
public class UploadRepositorioServiceImpl implements UploadRepositorioService {
	
	
    CMISManager cmisManager;
	
	public UploadRepositorioServiceImpl() {
    	Repository repository = new Repository();
        repository.setUser(PropiedadesAlfresco.getString("usuario"));
        repository.setPassword(PropiedadesAlfresco.getString("contrasenia"));
        repository.setHost(PropiedadesAlfresco.getString("ip"));
        repository.setPort(PropiedadesAlfresco.getString("puerto"));
        repository.setAtompub_url("default");

        CMISManagerBuilder builderManager = new CMISManagerBuilder();
        cmisManager = builderManager.build(repository);

    }
	
	public String grabarDocumento(ArchivoAdjuntoBean archivoAdjuntoBean,String tipoReporte) {
		
		try {
		return storeFile(archivoAdjuntoBean,createDirectory(archivoAdjuntoBean), archivoAdjuntoBean.getStream(),tipoReporte);
		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("No se pudo subir archivo al alfresco error: "+e.getMessage());
		
			return "fail";
		}
	}
	

	@Override
	public Object buscarDocumento(String path, String filename) {
		return cmisManager.cmisFindDocument(path, filename);
	}


    public String storeFile(ArchivoAdjuntoBean archivoAdjuntoBean,String path, byte[] bytes,String tipoReporte) {
    	

        FileProperties fp = new FileProperties();
        fp.addProp(PropertyIds.OBJECT_TYPE_ID, "D:sgicm:reporteDocumento");
        fp.addProp(PropertyIds.NAME, archivoAdjuntoBean.getNombre()+archivoAdjuntoBean.getTipo());
        fp.addProp(PropertyIds.CREATED_BY, "admin");
        
        fp.addProp("sgicm:fechaReporte", FechaUtil.fechaToString(new Date()));
        fp.addProp("sgicm:tipoReporte", tipoReporte);


        
//         return cmisManager.cmisUploadFile(fp,  bytes,"", path,FuncionesHelper.obtenerMimeTypeArchivo(archivoAdjuntoBean.getTipo()));
        return "";
    }

	
	
	public synchronized ByteArrayOutputStream convertInputStreamToOuputStream(InputStream inputStream) throws Exception{ 

        InputStream in = null;
			in =inputStream;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] content = new byte[ 2048 ];
        int bytesRead = -1;
        try {
			while( ( bytesRead = in.read( content ) ) != -1 ) {
			    baos.write( content, 0, bytesRead );
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

        try {
			in.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

        return baos;

    }
	

	public String createDirectory(ArchivoAdjuntoBean archivoAdjuntoBean) {
      String newFolder = "";
      String path=archivoAdjuntoBean.getRuta();

      newFolder = FechaUtil.getYear(archivoAdjuntoBean.getFeccre());
      createFolferIfNotExist(path,newFolder);

      path = path + "/" + newFolder;
      newFolder = FechaUtil.getMonth(archivoAdjuntoBean.getFeccre());
      createFolferIfNotExist(path, newFolder);

      path = path + "/" + newFolder;
      newFolder = FechaUtil.getDay(archivoAdjuntoBean.getFeccre());
      createFolferIfNotExist(path, newFolder);

      path = path + "/" + newFolder;

      return path.toString();

	}
	
    public void createFolferIfNotExist(String path, String newNameFolder){

        String fullPath;
        if("/".equals(path)){
            fullPath = path + newNameFolder;

        }else{
            fullPath = path + "/" + newNameFolder;
        }

        Boolean existFolder =  cmisManager.cmisExistsFolder(fullPath);

        if(existFolder.equals(false)){
            /*** Creamos el folder ***/
            cmisManager.cmisCreateFolder(path,newNameFolder);
        }
    }
}
