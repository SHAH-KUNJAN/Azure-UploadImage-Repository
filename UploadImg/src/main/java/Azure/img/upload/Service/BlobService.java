package Azure.img.upload.Service;

import java.io.InputStream;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import com.microsoft.azure.storage.blob.ListBlobItem;

import Azure.img.upload.connection.blobConnection;
@Service
public class BlobService {

	CloudStorageAccount storageAccount;
	CloudBlobClient blobClient;
	CloudBlobContainer container;
	CloudBlockBlob blob;
	
	BlobService() throws Exception{
		storageAccount = CloudStorageAccount.parse(blobConnection.Blob_Connection_String);
		blobClient = storageAccount.createCloudBlobClient();
		container = blobClient.getContainerReference("storageblob");
	}
	
	public ArrayList<String> getAllItemsLinks(String folderName) throws Exception{
		ArrayList<String> links=new ArrayList<String>();
		
		for (ListBlobItem blobItem : container.listBlobs()) {
			links.add(blobItem.getUri()+"");
		}
		return links;
	}
	public void uploadImage(InputStream is,String name,int length) throws Exception{
		 blob = container.getBlockBlobReference(name);
		 blob.upload(is,length);
		 sqlService.insert(blob.getUri()+"");
	}
}
