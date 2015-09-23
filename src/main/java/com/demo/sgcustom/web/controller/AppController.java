package com.demo.sgcustom.web.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.demo.sgcustom.web.FileUploadBean;
import com.google.gson.Gson;



public class AppController extends MultiActionController {


        public ModelAndView home(HttpServletRequest request, HttpServletResponse response) {
                System.out.println("Inside Controller****************home");
                ModelAndView mv=new ModelAndView("home");
                mv.addObject("msg", "Welcome!!!");
                mv.addObject("uploadFile", new FileUploadBean());
                return mv;
        }
        @ResponseBody
        public String listAllDocuments(HttpServletRequest request, HttpServletResponse response) {
        	System.out.println("===== list all doc");
        	response.setContentType("APPLICATION/JSON");
			PrintWriter out=null;
			HttpClient httpClient = HttpClientBuilder.create().build();
			//http://connector.cloudapps-613e.oslab.opentlc.com/
			HttpGet getRequest=new HttpGet("http://connector.cloudapps-613e.oslab.opentlc.com/document/list/all");
			getRequest.addHeader("accept", "application/json");
			BufferedReader br=null;
			
        	try {
    			HttpResponse httpResponse = httpClient.execute(getRequest);
    			if (httpResponse.getStatusLine().getStatusCode() != 200) {
    				throw new RuntimeException("Failed : HTTP error code : "
    				   + httpResponse.getStatusLine().getStatusCode());
    			}
    			br = new BufferedReader(
                        new InputStreamReader((httpResponse.getEntity().getContent())));			
    			String output;
    			StringBuffer sb=new StringBuffer();
    			System.out.println("Output from Server .... \n");
    			while ((output = br.readLine()) != null) {
    				sb.append(output);
    			}
    			Gson gson=new Gson();
    			String jsonStr=gson.toJson(sb.toString());
        		out=response.getWriter();
        		//out.println("{\"IsTruncated\":false,\"Marker\":\"\",\"Contents\":[{\"Key\":\"/DARREN001/shared/B001/darren.txt\",\"LastModified\":\"2015-08-03T01:45:45.000Z\",\"ETag\":\"\\\"f1e2620e80075d3c4e6c18cde68e87d5\\\"\",\"Size\":10611,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"/JSP01/shared/B001/darren.txt\",\"LastModified\":\"2015-08-03T01:35:11.000Z\",\"ETag\":\"\\\"f1e2620e80075d3c4e6c18cde68e87d5\\\"\",\"Size\":10611,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"910fredtest.jpg\",\"LastModified\":\"2015-09-10T05:01:04.000Z\",\"ETag\":\"\\\"2282d7205e979222c3f5dd22a1f3c624\\\"\",\"Size\":8068,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"AAAPOBioHealthpdf\",\"LastModified\":\"2015-09-18T02:54:04.000Z\",\"ETag\":\"\\\"bc9ea33344ca462c236aec4d4c7397da\\\"\",\"Size\":15171,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"CCPOBioHealthpdf\",\"LastModified\":\"2015-09-22T06:51:21.000Z\",\"ETag\":\"\\\"a76660ece72624e0b6116773bf7ab2c5\\\"\",\"Size\":91652,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"DDPOBioHealthpdf\",\"LastModified\":\"2015-09-22T08:15:02.000Z\",\"ETag\":\"\\\"a76660ece72624e0b6116773bf7ab2c5\\\"\",\"Size\":91652,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"EEPOBioHealthpdf\",\"LastModified\":\"2015-09-23T06:08:41.000Z\",\"ETag\":\"\\\"a76660ece72624e0b6116773bf7ab2c5\\\"\",\"Size\":91652,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"FFcertificatewine04pdf\",\"LastModified\":\"2015-09-17T08:04:04.000Z\",\"ETag\":\"\\\"bc9ea33344ca462c236aec4d4c7397da\\\"\",\"Size\":15171,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"Fcertificatewine22pdf\",\"LastModified\":\"2015-09-17T08:17:49.000Z\",\"ETag\":\"\\\"bc9ea33344ca462c236aec4d4c7397da\\\"\",\"Size\":15171,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"FreddieTest1.png\",\"LastModified\":\"2015-08-03T10:54:04.000Z\",\"ETag\":\"\\\"bc9ea33344ca462c236aec4d4c7397da\\\"\",\"Size\":15171,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"FreddieTest1sss.txt\",\"LastModified\":\"2015-09-17T07:03:33.000Z\",\"ETag\":\"\\\"bc9ea33344ca462c236aec4d4c7397da\\\"\",\"Size\":15171,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"FreddieTest2.txt\",\"LastModified\":\"2015-08-03T03:04:47.000Z\",\"ETag\":\"\\\"6cf6cf19819393ecd1ab163239a60991\\\"\",\"Size\":4334,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"FreddieTest221.pdf\",\"LastModified\":\"2015-08-13T20:35:22.000Z\",\"ETag\":\"\\\"d2fe999ccd7ca5dc00ac88d7fbac6bac\\\"\",\"Size\":443407,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"FreddieTest221.png\",\"LastModified\":\"2015-08-13T19:22:58.000Z\",\"ETag\":\"\\\"bc9ea33344ca462c236aec4d4c7397da\\\"\",\"Size\":15171,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"Invoice_Template.pdf\",\"LastModified\":\"2015-06-18T09:02:46.000Z\",\"ETag\":\"\\\"da51bb561ecac5dd450427f02760bdf2\\\"\",\"Size\":162478,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"JCcertificatewine04pdf\",\"LastModified\":\"2015-09-17T08:47:21.000Z\",\"ETag\":\"\\\"bc9ea33344ca462c236aec4d4c7397da\\\"\",\"Size\":15171,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"Suez Canal Demo - Sea Waybill.xml\",\"LastModified\":\"2015-08-06T08:30:21.000Z\",\"ETag\":\"\\\"b34be28fdc2dde44bf85e0a2595beec8\\\"\",\"Size\":12671,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"Suez Canal Demo Invoice.pdf\",\"LastModified\":\"2015-08-06T07:20:24.000Z\",\"ETag\":\"\\\"4a8ec9b6544039ccd2263328afe1f1d8\\\"\",\"Size\":48355,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"XXYcertificatewine04pdf\",\"LastModified\":\"2015-09-17T09:25:47.000Z\",\"ETag\":\"\\\"bc9ea33344ca462c236aec4d4c7397da\\\"\",\"Size\":15171,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"ZXcertificatewine04pdf\",\"LastModified\":\"2015-09-17T08:29:53.000Z\",\"ETag\":\"\\\"bc9ea33344ca462c236aec4d4c7397da\\\"\",\"Size\":15171,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"asd001\",\"LastModified\":\"2015-07-31T03:32:21.000Z\",\"ETag\":\"\\\"168820fda3cb9ddc960dbdeb96fd8dea\\\"\",\"Size\":158,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"certificatewine01pdf\",\"LastModified\":\"2015-09-17T07:19:17.000Z\",\"ETag\":\"\\\"bc9ea33344ca462c236aec4d4c7397da\\\"\",\"Size\":15171,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"certificatewine04pdf\",\"LastModified\":\"2015-09-17T07:59:18.000Z\",\"ETag\":\"\\\"bc9ea33344ca462c236aec4d4c7397da\\\"\",\"Size\":15171,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"certificatewineXXpdf\",\"LastModified\":\"2015-09-17T07:18:00.000Z\",\"ETag\":\"\\\"bc9ea33344ca462c236aec4d4c7397da\\\"\",\"Size\":15171,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"darroto.txt\",\"LastModified\":\"2015-08-03T02:49:50.000Z\",\"ETag\":\"\\\"f1e2620e80075d3c4e6c18cde68e87d5\\\"\",\"Size\":10611,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"hello123\",\"LastModified\":\"2015-07-30T07:26:46.000Z\",\"ETag\":\"\\\"6cf6cf19819393ecd1ab163239a60991\\\"\",\"Size\":4334,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"hi\",\"LastModified\":\"2015-06-19T06:31:57.000Z\",\"ETag\":\"\\\"da51bb561ecac5dd450427f02760bdf2\\\"\",\"Size\":162478,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"hithere\",\"LastModified\":\"2015-07-30T07:27:15.000Z\",\"ETag\":\"\\\"6cf6cf19819393ecd1ab163239a60991\\\"\",\"Size\":4334,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"leroaay.txt\",\"LastModified\":\"2015-08-11T01:42:05.000Z\",\"ETag\":\"\\\"f1e2620e80075d3c4e6c18cde68e87d5\\\"\",\"Size\":10611,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"leroy.txt\",\"LastModified\":\"2015-08-03T11:19:47.000Z\",\"ETag\":\"\\\"f1e2620e80075d3c4e6c18cde68e87d5\\\"\",\"Size\":10611,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"redHatgetFile\",\"LastModified\":\"2015-09-23T08:56:08.000Z\",\"ETag\":\"\\\"ff22941336956098ae9a564289d1bf1b\\\"\",\"Size\":15,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"redHatgetFileFromSession\",\"LastModified\":\"2015-09-23T08:54:06.000Z\",\"ETag\":\"\\\"5adf91c8a8ff93fc99a0fa8e0efb55ff\\\"\",\"Size\":16,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"redhat001\",\"LastModified\":\"2015-09-23T09:42:16.000Z\",\"ETag\":\"\\\"97d20e28475e4d7cb3f00c5a37f9e894\\\"\",\"Size\":65884,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"redhat002\",\"LastModified\":\"2015-09-23T10:14:12.000Z\",\"ETag\":\"\\\"97d20e28475e4d7cb3f00c5a37f9e894\\\"\",\"Size\":65884,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"redhat003\",\"LastModified\":\"2015-09-23T10:21:26.000Z\",\"ETag\":\"\\\"97d20e28475e4d7cb3f00c5a37f9e894\\\"\",\"Size\":65884,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"redhat123\",\"LastModified\":\"2015-09-23T09:31:31.000Z\",\"ETag\":\"\\\"97d20e28475e4d7cb3f00c5a37f9e894\\\"\",\"Size\":65884,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"redhat1234\",\"LastModified\":\"2015-09-23T09:33:57.000Z\",\"ETag\":\"\\\"97d20e28475e4d7cb3f00c5a37f9e894\\\"\",\"Size\":65884,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"redhat12345\",\"LastModified\":\"2015-09-23T09:34:56.000Z\",\"ETag\":\"\\\"97d20e28475e4d7cb3f00c5a37f9e894\\\"\",\"Size\":65884,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"redhat123456\",\"LastModified\":\"2015-09-23T09:37:00.000Z\",\"ETag\":\"\\\"97d20e28475e4d7cb3f00c5a37f9e894\\\"\",\"Size\":65884,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"test.txt\",\"LastModified\":\"2015-07-31T07:47:01.000Z\",\"ETag\":\"\\\"6706fb358e74572f0dde6568285c426c\\\"\",\"Size\":13,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"test/\",\"LastModified\":\"2015-07-31T03:33:30.000Z\",\"ETag\":\"\\\"d41d8cd98f00b204e9800998ecf8427e\\\"\",\"Size\":0,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"test_freddie.docx\",\"LastModified\":\"2015-08-13T20:52:09.000Z\",\"ETag\":\"\\\"55e3920c57b844bea1bdce0fbd5cb417\\\"\",\"Size\":18286,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"testing\",\"LastModified\":\"2015-06-19T07:43:11.000Z\",\"ETag\":\"\\\"da51bb561ecac5dd450427f02760bdf2\\\"\",\"Size\":162478,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}},{\"Key\":\"tokyo1.txt\",\"LastModified\":\"2015-07-31T06:20:25.000Z\",\"ETag\":\"\\\"127220b968411505036a1cac2b623fa6\\\"\",\"Size\":4643,\"StorageClass\":\"STANDARD\",\"Owner\":{\"DisplayName\":\"melven.a.chok\",\"ID\":\"dc43700ac7622c63a52a79982c3785b027f783b3ff7f7553df174b1bff15f132\"}}],\"Name\":\"ntidemo\",\"Prefix\":\"\",\"MaxKeys\":1000,\"CommonPrefixes\":[]}");
        		System.out.println(jsonStr);
        		out.println(jsonStr);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	finally {
        		httpClient.getConnectionManager().shutdown();
            	out.flush();
            	out.close();
        	}
        	return null;
        }
        @ResponseBody
        public String getFileFromSession(HttpServletRequest request, HttpServletResponse response) {
        	String fileName=request.getParameter("file");
			//TEST using session
        	byte[] sessionFile=(byte[])request.getServletContext().getAttribute("UPLOADED_FILE_"+fileName);
        	if (sessionFile ==null) {
        		try {
					response.getWriter().println("File Not Found");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		return null;
        	}
        	else {
	        	//PrintWriter out=null;
				response.setContentType("APPLICATION/OCTET-STREAM"); 
	            response.setHeader("Content-disposition","attachment; filename="+fileName);
	            try {
	            	System.out.println("============== "+response);
	            	System.out.println("============== "+response.getOutputStream());
	            	System.out.println("============== "+sessionFile);
	            	response.getOutputStream().write(sessionFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
        	}//else
       }
        @ResponseBody
        public String getFile(HttpServletRequest request, HttpServletResponse response) {
        	String fileName=request.getParameter("file");
        	File f=null;
        	String uploadLocation=request.getServletContext().getInitParameter("UPLOAD_DIR");
        	PrintWriter out=null;
        	//response.setContentType("text/html");  
        	response.setContentType("APPLICATION/OCTET-STREAM"); 
            //response.setHeader("Content-disposition","attachment; filename=yourcustomfilename.pdf");
            response.setHeader("Content-disposition","attachment; filename="+fileName);

        	FileInputStream fi=null;
			try {
				out = response.getWriter();
				f=new File(uploadLocation+fileName);
				
				fi=new FileInputStream(f);
				int i;
				while ((i=fi.read())!=-1) {
					out.write(i);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					if (fi!=null)
						fi.close();	
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				out.flush();
	        	out.close();
				
			}
				
        	return null;

        }

        
        @ResponseBody
        public String uploadFile(HttpServletRequest request, HttpServletResponse response, FileUploadBean file) {
        	System.out.println("===============File "+file);
        	System.out.println("===============File Name "+request.getParameter("fileName"));
        	String fileName=request.getParameter("fileName");
        	
        	String uploadLocation=request.getServletContext().getInitParameter("UPLOAD_DIR");
        	System.out.println("===============Upload to "+uploadLocation+fileName);
            BufferedOutputStream stream;
            try {
            	//TEST to save in session // does not work for session, 3rd party cannot access
            	request.getSession().setAttribute("UPLOADED_FILE_"+fileName, file.getUploadFile().getBytes());
            	request.getSession().setAttribute("FILE_NAME", fileName);
            	request.getServletContext().setAttribute("UPLOADED_FILE_"+fileName, file.getUploadFile().getBytes());
            	request.getServletContext().setAttribute("FILE_NAME", fileName);
				System.out.println("===============upload file "+file.getUploadFile());
				byte[] bytes = file.getUploadFile().getBytes();
	        	System.out.println("===============Bytes "+bytes);
				stream = new BufferedOutputStream(new FileOutputStream(uploadLocation+fileName));
	            stream.write(bytes);
	            stream.close();        	

			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	response.setContentType("text/html");
        	PrintWriter out=null;
			try {
				
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	//out.print("{id:a, name:"+fileName+"}");
        	out.print(fileName +" added to company vault");
        	out.flush();
        	out.close();
        	return null;
        	
        }
        
        

}
