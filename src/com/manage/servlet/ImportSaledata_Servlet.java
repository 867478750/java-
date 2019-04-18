package com.manage.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.poi.ss.usermodel.Cell;

import com.manage.bean.Salemodel;
import com.manage.excel.ExcelColumn;
import com.manage.excel.ExcelHead;
import com.manage.excel.ExcelHelper;

public class ImportSaledata_Servlet extends HttpServlet implements
		javax.servlet.Servlet {
	private static final long serialVersionUID = 1L;

	public ImportSaledata_Servlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		String name = (String) session.getAttribute("name");
		if (name == null || name.equals("") || name.equals("null"))
			resp.sendRedirect("login.jsp");
		else {
			try {
				File tmpDir = new File(getServletContext().getRealPath("/tmp/"));// 初始化上传文件的临时存放目录
				if (ServletFileUpload.isMultipartContent(req)) {
					DiskFileItemFactory dff = new DiskFileItemFactory();// 创建该对象
					dff.setRepository(tmpDir);// 指定上传文件的临时目录
					dff.setSizeThreshold(1024 * 4);// 指定在内存中缓存数据大小,单位为byte
					ServletFileUpload sfu = new ServletFileUpload(dff);// 创建该对象
					sfu.setFileSizeMax(1024 * 1024 * 10);// 指定单个上传文件的最大尺寸
					sfu.setSizeMax(1024 * 1024 * 10);// 指定一次上传多个文件的总尺寸
					FileItemIterator fii = sfu.getItemIterator(req);// 解析request
																	// 请求,并返回FileItemIterator集合
					while (fii.hasNext()) {
						FileItemStream fis = fii.next();// 从集合中获得一个文件流
						if (!fis.isFormField() && fis.getName().length() > 0) {// 过滤掉表单中非文件域

							BufferedInputStream in = new BufferedInputStream(
									fis.openStream());// 获得文件输入流
							String fileName = "data"
									+ UUID.randomUUID().toString() + ".tmp";
							BufferedOutputStream out = new BufferedOutputStream(
									new FileOutputStream(new File(tmpDir
											+ fileName)));// 获得文件输出流
							Streams.copy(in, out, true);// 开始把文件写到你指定的上传文件夹

							List<ExcelColumn> excelColumns = new ArrayList<ExcelColumn>();
							excelColumns.add(new ExcelColumn(0, "city", "城市"));
							excelColumns.add(new ExcelColumn(1, "product", "产品"));
							excelColumns.add(new ExcelColumn(2, "num", "数量",Cell.CELL_TYPE_STRING));
							excelColumns.add(new ExcelColumn(3, "salesman","推销员"));
							excelColumns.add(new ExcelColumn(4, "remark", "备注"));
							for (int i = 0; i < excelColumns.size(); i++) {
								System.out.println("excelColumns===="
										+ excelColumns.get(i).toString());
							}

							/*
							 * // 需要特殊转换的单元 Map<String, Map>
							 * excelColumnsConvertMap = new HashMap<String,
							 * Map>(); Map<String, Integer> isReceive = new
							 * HashMap<String, Integer>(); isReceive.put("是",
							 * 1); isReceive.put("否", 0);
							 * excelColumnsConvertMap.put("isReceive",
							 * isReceive); Map<String, Integer> orderType = new
							 * HashMap<String, Integer>(); orderType.put("新订单",
							 * 1); orderType.put("续订订单", 2);
							 * excelColumnsConvertMap.put("orderType",
							 * orderType);
							 */

							File sourceFile = new File(tmpDir + fileName);
							ExcelHead head = new ExcelHead();
							head.setRowCount(1); // 头所占行数
							head.setColumns(excelColumns); // 列的定义
							// head.setColumnsConvertMap(excelColumnsConvertMap);
							// 列的转换

							@SuppressWarnings("unchecked")
							List<Salemodel> list = ExcelHelper.getInstanse()
									.importToObjectList(head, sourceFile,
											Salemodel.class);
							List<Salemodel> ulist = new ArrayList<Salemodel>();
							for (Iterator iterrator = list.iterator(); iterrator
									.hasNext();) {
								Salemodel detail = (Salemodel) iterrator.next();
								ulist.add(detail);
							}

							req.setAttribute("ulist", ulist);
							req.setAttribute("ulistlong", ulist.size());
							RequestDispatcher dispatcher = req
									.getRequestDispatcher("ImportSaledata.jsp");
							dispatcher.forward(req, resp);
						}
					}
				}

			} catch (FileUploadException e) {
				e.printStackTrace();
			}
		}
	}
}
