package com.archsystemsinc.pqrs.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.archsystemsinc.pqrs.model.DocumentUpload;
import com.archsystemsinc.pqrs.model.ProviderHypothesis;
import com.archsystemsinc.pqrs.model.ReportingOptionLookup;
import com.archsystemsinc.pqrs.model.YearLookup;
import com.archsystemsinc.pqrs.service.ParameterLookUpService;
import com.archsystemsinc.pqrs.service.ProviderHypothesisService;
import com.archsystemsinc.pqrs.service.ReportingOptionLookUpService;
import com.archsystemsinc.pqrs.service.YearLookUpService;

/**
 * controller class for file upload and insert functionality of provider hypothesis, 
 * state wise statistics, and specialty data
 * 
 * @author Grmahun Redda
 * @since 6/20/2017
 */
@Controller
public class DocumentUploadController {
	
	@Autowired
	private ProviderHypothesisService providerHypothesisService;
	
	@Autowired
	private ParameterLookUpService parameterLookUpService;
	
	@Autowired
	private ReportingOptionLookUpService reportingOptionLookUpService;
	
	@Autowired
	private YearLookUpService yearLookUpService;
	
	
	@RequestMapping(value = "/admin/documentupload", method = RequestMethod.GET)
	public String documentUploadGet(final Model model, HttpSession session) {		
		
		model.addAttribute("documentFileUpload", new DocumentUpload());
		
		return "uploadform";
	}
	
	@RequestMapping(value = "/admin/documentupload", method = RequestMethod.POST)
	public String documentUploadPost(final Model model,
			@Valid@ModelAttribute("documentFileUpload") final DocumentUpload documentFileUpload, final Principal principal,
			final BindingResult result, final HttpServletRequest request) throws InvalidFormatException {		
		
		try {
			documentUploadProvider(documentFileUpload);
			model.addAttribute("documentuploadsuccess","success.save.questions");
		}catch (Exception e) {
			System.out.println("Exception in Documents Upload page: " + e.getMessage());			
			model.addAttribute("documentuploaderror","error.save.document");
		}		
		
		return "uploadform";
	}
	
   
	public void documentUploadProvider(
			final DocumentUpload documentFileUpload) throws InvalidFormatException, EncryptedDocumentException, IOException {
		
		int totalNumberOfRows = 0;
		int totalProRowsCreatedOrUpdated = 0;
		ArrayList<Object> returnObjects = null;
		
		//try {
			
			if (documentFileUpload.getProvider() != null) {
				
				Workbook providersFileWorkbook = WorkbookFactory.create(documentFileUpload.getProvider().getInputStream());
				Sheet providersFileSheet = providersFileWorkbook.getSheetAt(0);
				Iterator<Row> providersFileRowIterator = providersFileSheet.rowIterator();
                int providersFileRowCount = providersFileSheet.getPhysicalNumberOfRows();
				totalNumberOfRows = providersFileRowCount - 1;
				String stringResult = "";

			
				
				//long yearId =  2;

				while (providersFileRowIterator.hasNext()) 
				{
					Row providersFileRow = (Row) providersFileRowIterator.next();
					
					returnObjects = new ArrayList<Object>();
					
					if (providersFileRow.getRowNum() > 0 && providersFileRow.getRowNum() <= providersFileRowCount)
					{
						System.out.println("ROW - " + providersFileRow.getRowNum());
						Iterator<Cell> iterator = providersFileRow.cellIterator();
						ProviderHypothesis provider = new ProviderHypothesis();
						
						
						while (iterator.hasNext()) 
						{
							Cell hssfCell = (Cell) iterator.next();
							int cellIndex = hssfCell.getColumnIndex();
							
							switch (cellIndex) 
							{
							
							case 1:
								switch (hssfCell.getCellType())
								{
								
				                case Cell.CELL_TYPE_STRING:					                	
				                    stringResult=hssfCell.getStringCellValue();
				                    provider.setYearLookup(yearLookUpService.findByYearName(stringResult));
				                    System.out.println("Year name: " + stringResult);
				                  
				                    break;
								
								}
								break;								
							case 2:
								switch (hssfCell.getCellType())
								{
								
				                case Cell.CELL_TYPE_STRING:	
				                	
				                    stringResult=hssfCell.getStringCellValue();
				                    provider.setReportingOptionLookup(reportingOptionLookUpService.findByReportingOptionName(stringResult));
				                    break;	
								
								}
								break;
	
							case 3:
								switch (hssfCell.getCellType()) 
								{
								
				                case Cell.CELL_TYPE_STRING:	
				                	
				                    stringResult=hssfCell.getStringCellValue();
				                    provider.setParameterLookup(parameterLookUpService.findByParameterName(stringResult));				                   
				                    break;
								
								}
								break;
							case 4:
								switch (hssfCell.getCellType())
								{
								
				                case Cell.CELL_TYPE_NUMERIC:	
				                    provider.setYesValue((int)hssfCell.getNumericCellValue());
				                    break;
								
								}
								break;
							case 5:
								switch (hssfCell.getCellType())
								{
								
				                case Cell.CELL_TYPE_NUMERIC:	
				                    provider.setNoValue((int)hssfCell.getNumericCellValue());
				                    break;
								
								}
								break;
							case 6:
								switch (hssfCell.getCellType())
								{
								
				                case Cell.CELL_TYPE_NUMERIC:	
				                	provider.setYesCount(BigInteger.valueOf((int)hssfCell.getNumericCellValue()));				                   
				                    break;								
								}
								break;
							case 7:
								switch (hssfCell.getCellType())
								{
								
				                case Cell.CELL_TYPE_NUMERIC:	
				                	provider.setNoCount(BigInteger.valueOf((int)hssfCell.getNumericCellValue()));
				                    break;								
								}
								break;
							case 8:
								switch (hssfCell.getCellType())
								{
								
				                case Cell.CELL_TYPE_NUMERIC:	
				                	provider.setYesPercent(hssfCell.getNumericCellValue());
				                    break;								
								}
								break;
							case 9:
								switch (hssfCell.getCellType())
								{
								
				                case Cell.CELL_TYPE_NUMERIC:	
				                	provider.setNoPercent(hssfCell.getNumericCellValue());
				                    break;								
								}
								break;
							case 10:
								switch (hssfCell.getCellType())
								{
								
				                case Cell.CELL_TYPE_NUMERIC:	
				                	provider.setTotalSum(BigInteger.valueOf((int)hssfCell.getNumericCellValue()));
				                	providerHypothesisService.create(provider);				                    
				                    break;								
								}
								break;		
							}


						}
						
						
					}
 
				}

			}			

	}

}
