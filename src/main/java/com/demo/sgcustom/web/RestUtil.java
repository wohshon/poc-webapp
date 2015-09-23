package com.demo.sgcustom.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;

public class RestUtil {
	
	public static void main(String[] args) {
		RestUtil ru=new RestUtil();
		//ru.rest();
		ru.post();
	}
	
	public void post() {
		HttpClient httpClient = HttpClientBuilder.create().build();
		//http://54.169.32.213/document/add
		HttpPost postRequest=new HttpPost("http://192.168.223.198:9000/document/add");
		//HttpPost postRequest=new HttpPost("http://54.169.32.213/document/add");
		try {
			//StringEntity input =new StringEntity("{\"inputFile\":\"http://sg-demo.cloudapps-613e.oslab.opentlc.com/getFile.page?file=sample.json\",\"documentName\":\"sample.json\",\"documentType\":\"application/octet-stream\"}");
			//StringEntity input =new StringEntity("{\"inputFile\":\"http://sg-demo.cloudapps-613e.oslab.opentlc.com/getFile.page?file=poc.txt\",\"documentName\":\"poc.txt\",\"documentType\":\"plain/text\"}");
			StringEntity input =new StringEntity("{\"inputFile\":\"http://192.168.223.198:8080/getFile.page?file=poc.txt\",\"documentName\":\"poc.txt\",\"documentType\":\"plain/text\"}");
			input.setContentType("application/json");
			postRequest.setEntity(input);

			HttpResponse response = httpClient.execute(postRequest);

			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(
	                        new InputStreamReader((response.getEntity().getContent())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}			
		
			System.out.println((response.getEntity().getContentLength()));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		finally {
			httpClient.getConnectionManager().shutdown();

		}
	}
	public void rest() {
		
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet getRequest=new HttpGet("http://192.168.223.198:9000/document/list/all");
		getRequest.addHeader("accept", "application/json");
		BufferedReader br=null;
		try {
			HttpResponse response = httpClient.execute(getRequest);
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
				   + response.getStatusLine().getStatusCode());
			}
			br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));			
			String output;
			StringBuffer sb=new StringBuffer();
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				//System.out.println(output);
				sb.append(output);
			}
			Gson gson=new Gson();
			
			String s="{\"IsTruncated\":true,\"Marker\":\"123\",\"Contents\":[{\"Key\":\"/DARREN001/shared/B001/darren.txt\",\"LastModified\":\"2015-08-03T01:45:45.000Z\",\"ETag\":\"\\\"f1e2620e80075d3c4e6c18cde68e87d5\\\"\",\"Size\":10611,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}}],\"Name\":\"ntidemo\",\"Prefix\":\"\",\"MaxKeys\":1000,\"CommonPrefixes\":[]}";
			//String s="{\"IsTruncated\":true,\"Marker\":\"123\",\"Contents\":[{\"Key\":\"/DARREN001/shared/B001/darren.txt\",\"LastModified\":\"2015-08-03T01:45:45.000Z\",\"ETag\":\"\\\"f1e2620e80075d3c4e6c18cde68e87d5\\\"\",\"Size\":10611,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}}]}";

			String jsonStr=gson.toJson(sb.toString());
			System.out.println(sb.toString());
			System.out.println(jsonStr);
			String ss="{\"IsTruncated\":false,\"Marker\":\"\",\"Contents\":[{\"Key\":\"/DARREN001/shared/B001/darren.txt\",\"LastModified\":\"2015-08-03T01:45:45.000Z\",\"ETag\":\"\\\"f1e2620e80075d3c4e6c18cde68e87d5\\\"\",\"Size\":10611,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"/JSP01/shared/B001/darren.txt\",\"LastModified\":\"2015-08-03T01:35:11.000Z\",\"ETag\":\"\\\"f1e2620e80075d3c4e6c18cde68e87d5\\\"\",\"Size\":10611,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"910fredtest.jpg\",\"LastModified\":\"2015-09-10T05:01:04.000Z\",\"ETag\":\"\\\"2282d7205e979222c3f5dd22a1f3c624\\\"\",\"Size\":8068,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"AAAPOBioHealthpdf\",\"LastModified\":\"2015-09-18T02:54:04.000Z\",\"ETag\":\"\\\"bc9ea33344ca462c236aec4d4c7397da\\\"\",\"Size\":15171,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"CCPOBioHealthpdf\",\"LastModified\":\"2015-09-22T06:51:21.000Z\",\"ETag\":\"\\\"a76660ece72624e0b6116773bf7ab2c5\\\"\",\"Size\":91652,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"DDPOBioHealthpdf\",\"LastModified\":\"2015-09-22T08:15:02.000Z\",\"ETag\":\"\\\"a76660ece72624e0b6116773bf7ab2c5\\\"\",\"Size\":91652,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"EEPOBioHealthpdf\",\"LastModified\":\"2015-09-23T06:08:41.000Z\",\"ETag\":\"\\\"a76660ece72624e0b6116773bf7ab2c5\\\"\",\"Size\":91652,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"FFcertificatewine04pdf\",\"LastModified\":\"2015-09-17T08:04:04.000Z\",\"ETag\":\"\\\"bc9ea33344ca462c236aec4d4c7397da\\\"\",\"Size\":15171,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"Fcertificatewine22pdf\",\"LastModified\":\"2015-09-17T08:17:49.000Z\",\"ETag\":\"\\\"bc9ea33344ca462c236aec4d4c7397da\\\"\",\"Size\":15171,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"FreddieTest1.png\",\"LastModified\":\"2015-08-03T10:54:04.000Z\",\"ETag\":\"\\\"bc9ea33344ca462c236aec4d4c7397da\\\"\",\"Size\":15171,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"FreddieTest1sss.txt\",\"LastModified\":\"2015-09-17T07:03:33.000Z\",\"ETag\":\"\\\"bc9ea33344ca462c236aec4d4c7397da\\\"\",\"Size\":15171,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"FreddieTest2.txt\",\"LastModified\":\"2015-08-03T03:04:47.000Z\",\"ETag\":\"\\\"6cf6cf19819393ecd1ab163239a60991\\\"\",\"Size\":4334,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"FreddieTest221.pdf\",\"LastModified\":\"2015-08-13T20:35:22.000Z\",\"ETag\":\"\\\"d2fe999ccd7ca5dc00ac88d7fbac6bac\\\"\",\"Size\":443407,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"FreddieTest221.png\",\"LastModified\":\"2015-08-13T19:22:58.000Z\",\"ETag\":\"\\\"bc9ea33344ca462c236aec4d4c7397da\\\"\",\"Size\":15171,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"Invoice_Template.pdf\",\"LastModified\":\"2015-06-18T09:02:46.000Z\",\"ETag\":\"\\\"da51bb561ecac5dd450427f02760bdf2\\\"\",\"Size\":162478,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"JCcertificatewine04pdf\",\"LastModified\":\"2015-09-17T08:47:21.000Z\",\"ETag\":\"\\\"bc9ea33344ca462c236aec4d4c7397da\\\"\",\"Size\":15171,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"Suez Canal Demo - Sea Waybill.xml\",\"LastModified\":\"2015-08-06T08:30:21.000Z\",\"ETag\":\"\\\"b34be28fdc2dde44bf85e0a2595beec8\\\"\",\"Size\":12671,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"Suez Canal Demo Invoice.pdf\",\"LastModified\":\"2015-08-06T07:20:24.000Z\",\"ETag\":\"\\\"4a8ec9b6544039ccd2263328afe1f1d8\\\"\",\"Size\":48355,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"XXYcertificatewine04pdf\",\"LastModified\":\"2015-09-17T09:25:47.000Z\",\"ETag\":\"\\\"bc9ea33344ca462c236aec4d4c7397da\\\"\",\"Size\":15171,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"ZXcertificatewine04pdf\",\"LastModified\":\"2015-09-17T08:29:53.000Z\",\"ETag\":\"\\\"bc9ea33344ca462c236aec4d4c7397da\\\"\",\"Size\":15171,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"asd001\",\"LastModified\":\"2015-07-31T03:32:21.000Z\",\"ETag\":\"\\\"168820fda3cb9ddc960dbdeb96fd8dea\\\"\",\"Size\":158,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"certificatewine01pdf\",\"LastModified\":\"2015-09-17T07:19:17.000Z\",\"ETag\":\"\\\"bc9ea33344ca462c236aec4d4c7397da\\\"\",\"Size\":15171,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"certificatewine04pdf\",\"LastModified\":\"2015-09-17T07:59:18.000Z\",\"ETag\":\"\\\"bc9ea33344ca462c236aec4d4c7397da\\\"\",\"Size\":15171,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"certificatewineXXpdf\",\"LastModified\":\"2015-09-17T07:18:00.000Z\",\"ETag\":\"\\\"bc9ea33344ca462c236aec4d4c7397da\\\"\",\"Size\":15171,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"darroto.txt\",\"LastModified\":\"2015-08-03T02:49:50.000Z\",\"ETag\":\"\\\"f1e2620e80075d3c4e6c18cde68e87d5\\\"\",\"Size\":10611,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"hello123\",\"LastModified\":\"2015-07-30T07:26:46.000Z\",\"ETag\":\"\\\"6cf6cf19819393ecd1ab163239a60991\\\"\",\"Size\":4334,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"hi\",\"LastModified\":\"2015-06-19T06:31:57.000Z\",\"ETag\":\"\\\"da51bb561ecac5dd450427f02760bdf2\\\"\",\"Size\":162478,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"hithere\",\"LastModified\":\"2015-07-30T07:27:15.000Z\",\"ETag\":\"\\\"6cf6cf19819393ecd1ab163239a60991\\\"\",\"Size\":4334,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"leroaay.txt\",\"LastModified\":\"2015-08-11T01:42:05.000Z\",\"ETag\":\"\\\"f1e2620e80075d3c4e6c18cde68e87d5\\\"\",\"Size\":10611,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"leroy.txt\",\"LastModified\":\"2015-08-03T11:19:47.000Z\",\"ETag\":\"\\\"f1e2620e80075d3c4e6c18cde68e87d5\\\"\",\"Size\":10611,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"redHatgetFile\",\"LastModified\":\"2015-09-23T08:56:08.000Z\",\"ETag\":\"\\\"ff22941336956098ae9a564289d1bf1b\\\"\",\"Size\":15,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"redHatgetFileFromSession\",\"LastModified\":\"2015-09-23T08:54:06.000Z\",\"ETag\":\"\\\"5adf91c8a8ff93fc99a0fa8e0efb55ff\\\"\",\"Size\":16,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"redhat001\",\"LastModified\":\"2015-09-23T09:42:16.000Z\",\"ETag\":\"\\\"97d20e28475e4d7cb3f00c5a37f9e894\\\"\",\"Size\":65884,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"redhat002\",\"LastModified\":\"2015-09-23T10:14:12.000Z\",\"ETag\":\"\\\"97d20e28475e4d7cb3f00c5a37f9e894\\\"\",\"Size\":65884,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"redhat003\",\"LastModified\":\"2015-09-23T10:21:26.000Z\",\"ETag\":\"\\\"97d20e28475e4d7cb3f00c5a37f9e894\\\"\",\"Size\":65884,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"redhat123\",\"LastModified\":\"2015-09-23T09:31:31.000Z\",\"ETag\":\"\\\"97d20e28475e4d7cb3f00c5a37f9e894\\\"\",\"Size\":65884,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"redhat1234\",\"LastModified\":\"2015-09-23T09:33:57.000Z\",\"ETag\":\"\\\"97d20e28475e4d7cb3f00c5a37f9e894\\\"\",\"Size\":65884,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"redhat12345\",\"LastModified\":\"2015-09-23T09:34:56.000Z\",\"ETag\":\"\\\"97d20e28475e4d7cb3f00c5a37f9e894\\\"\",\"Size\":65884,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"redhat123456\",\"LastModified\":\"2015-09-23T09:37:00.000Z\",\"ETag\":\"\\\"97d20e28475e4d7cb3f00c5a37f9e894\\\"\",\"Size\":65884,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"test.txt\",\"LastModified\":\"2015-07-31T07:47:01.000Z\",\"ETag\":\"\\\"6706fb358e74572f0dde6568285c426c\\\"\",\"Size\":13,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"test/\",\"LastModified\":\"2015-07-31T03:33:30.000Z\",\"ETag\":\"\\\"d41d8cd98f00b204e9800998ecf8427e\\\"\",\"Size\":0,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"test_freddie.docx\",\"LastModified\":\"2015-08-13T20:52:09.000Z\",\"ETag\":\"\\\"55e3920c57b844bea1bdce0fbd5cb417\\\"\",\"Size\":18286,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"testing\",\"LastModified\":\"2015-06-19T07:43:11.000Z\",\"ETag\":\"\\\"da51bb561ecac5dd450427f02760bdf2\\\"\",\"Size\":162478,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"tokyo1.txt\",\"LastModified\":\"2015-07-31T06:20:25.000Z\",\"ETag\":\"\\\"127220b968411505036a1cac2b623fa6\\\"\",\"Size\":4643,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}}],\"Name\":\"ntidemo\",\"Prefix\":\"\",\"MaxKeys\":1000,\"CommonPrefixes\":[]}";
			Payload payload=gson.fromJson(ss, Payload.class);
			System.out.println(payload.getMarker());
			System.out.println(payload.isTruncated());			
			System.out.println(payload.getContents()[0].getOwner().getDisplayName());			
			System.out.println(payload.getContents().length);			
			System.out.println(payload.getContents()[0].getETag());	
			System.out.println(payload.getMaxKeys());
			System.out.println(gson.toJson(payload));
			
/*			DocOwner o=null;;
			String json="{\"ID\":\"123\", \"displayName\":\"abc\"}";
			String jsonArray="[{\"ID\":\"123\", \"displayName\":\"abc\"}]";
			o=gson.fromJson(json, DocOwner.class);
			DocOwner[] oarray=gson.fromJson(jsonArray, DocOwner[].class);
			System.out.println(o.getID());
			System.out.println(o.getDisplayName());
			System.out.println(oarray.length);
*//*			//Payload p=gson.fromJson("{documents:["+sb.toString()+"]}", Payload.class);
			System.out.println("{\"documents\":["+sb.toString()+"]}");
			Payload p=gson.fromJson("{\"documents\":["+sb.toString()+"]}", Payload.class);
			System.out.println(p.getDocuments().get(0).getName());
			DocOwner o=gson.fromJson("{\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}}", DocOwner.class);
			System.out.println(o.getDisplayName());
*/			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			httpClient.getConnectionManager().shutdown();
		}

	}
	
	class DocOwner {
		private String ID;
		private String DisplayName;
		public String getID() {
			return ID;
		}
		public void setID(String ID) {
			this.ID = ID;
		}
		public String getDisplayName() {
			return DisplayName;
		}
		public void setDisplayName(String displayName) {
			this.DisplayName = DisplayName;
		}
		
		
	}
	class DocDetails {
		private String Key;
		private String LastModified;
		private String ETag;
		private String Size;
		private String StorageClass;
		private DocOwner Owner;
		public String getKey() {
			return Key;
		}
		public void setKey(String key) {
			Key = key;
		}
		public String getLastModified() {
			return LastModified;
		}
		public void setLastModified(String lastModified) {
			LastModified = lastModified;
		}
		public String getETag() {
			return ETag;
		}
		public void setETag(String eTag) {
			ETag = eTag;
		}
		public String getSize() {
			return Size;
		}
		public void setSize(String size) {
			Size = size;
		}
		public String getStorageClass() {
			return StorageClass;
		}
		public void setStorageClass(String storageClass) {
			StorageClass = storageClass;
		}
		public DocOwner getOwner() {
			return Owner;
		}
		public void setOwner(DocOwner owner) {
			Owner = owner;
		}
		
		
		
	}
	class Payload {
		
		private boolean IsTruncated;
		private String Marker;
		private DocDetails[] Contents;
		private String Name;
		private String Prefix;
		private String MaxKeys;
		private String[] CommonPrefixes;
		
		
		
		public String getPrefix() {
			return Prefix;
		}
		public void setPrefix(String prefix) {
			Prefix = prefix;
		}
		public String getName() {
			return Name;
		}
		public void setName(String name) {
			Name = name;
		}
		public String getMaxKeys() {
			return MaxKeys;
		}
		public void setMaxKeys(String maxKeys) {
			MaxKeys = maxKeys;
		}
		public String[] getCommonPrefixes() {
			return CommonPrefixes;
		}
		public void setCommonPrefixes(String[] commonPrefixes) {
			CommonPrefixes = commonPrefixes;
		}
		public DocDetails[] getContents() {
			return Contents;
		}
		public void setContents(DocDetails[] contents) {
			Contents = contents;
		}
		public boolean isTruncated() {
			return IsTruncated;
		}
		public void setIsTruncated(boolean truncated) {
			this.IsTruncated = truncated;
		}
		public String getMarker() {
			return Marker;
		}
		public void setMarker(String marker) {
			this.Marker = marker;
		}
		
	}
	
}
