package Azure.img.upload.Service;

import java.io.InputStream;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class imgService {
	@Autowired
	BlobService storageService;
	public ArrayList<String> getImages(String keyword) throws Exception{
		ArrayList<String> links=null;
		
		links= storageService.getAllItemsLinks("images");

		return links;
	}
	public void uploadImage(InputStream is,String name,int length) throws Exception {
			storageService.uploadImage(is, name, length);
	}
}
