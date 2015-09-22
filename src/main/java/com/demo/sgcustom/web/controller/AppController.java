package com.demo.sgcustom.web.controller;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.demo.sgcustom.web.FileUploadBean;



public class AppController extends MultiActionController {


        public ModelAndView home(HttpServletRequest request, HttpServletResponse response) {
                System.out.println("Inside Controller****************home");
                ModelAndView mv=new ModelAndView("home");
                mv.addObject("msg", "Welcome!!!");
                mv.addObject("uploadFile", new FileUploadBean());
                return mv;
        }
        @ResponseBody
        public String getFileFromSession(HttpServletRequest request, HttpServletResponse response) {
        	String fileName=request.getParameter("file");
			//TEST using session
        	byte[] sessionFile=(byte[])request.getSession().getAttribute("UPLOADED_FILE_"+fileName);
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
            	//TEST to save in session
            	request.getSession().setAttribute("UPLOADED_FILE_"+fileName, file.getUploadFile().getBytes());
            	request.getSession().setAttribute("FILE_NAME", fileName);
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
