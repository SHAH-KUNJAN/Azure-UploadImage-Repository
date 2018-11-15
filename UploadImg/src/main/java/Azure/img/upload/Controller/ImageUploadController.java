package Azure.img.upload.Controller;

import java.util.ArrayList;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import Azure.img.upload.Service.BlobService;
import Azure.img.upload.Service.imgService;
import Azure.img.upload.Service.sqlService;

@RestController
public class ImageUploadController {
	@Autowired
	imgService imgservice;
	@RequestMapping(value = "/get/check", method = RequestMethod.GET) 
	public void check() {
		System.out.println("check it out123466789552");
	}
	@RequestMapping(value = "/get/{key}", method = RequestMethod.GET)
	public ResponseEntity<String> getImages(@PathVariable String key)
	{
		System.out.println("check it out");
		try
		{
			ArrayList<String> l;
			if(key.equals("all"))
				l=imgservice.getImages("");
			else
				l=sqlService.getlink(key);
			JSONArray jarr=new JSONArray();
			
			for(int i=0;i<l.size();i++)
				jarr.put(l.get(i));
			
			return new ResponseEntity(jarr.toString(),HttpStatus.OK);
		}catch(Exception e)
		{
			return new ResponseEntity("Something went wrong...!!!",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@RequestMapping(value = "/uploadImages", method = RequestMethod.POST)
	public ResponseEntity<String> uploadImages(@RequestParam("files") MultipartFile[] files) {
		try {
			for(int i=0;i<files.length;i++) {
				imgservice.uploadImage(files[i].getInputStream(),files[i].getOriginalFilename(),Integer.parseInt(files[i].getSize()+""));
			}
			return new ResponseEntity("sucess",HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity("Something went wrong...!!!",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
