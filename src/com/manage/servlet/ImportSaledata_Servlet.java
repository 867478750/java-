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
				File tmpDir = new File(getServletContext().getRealPath("/tmp/"));// ��ʼ���ϴ��ļ�����ʱ���Ŀ¼
				if (ServletFileUpload.isMultipartContent(req)) {
					DiskFileItemFactory dff = new DiskFileItemFactory();// �����ö���
					dff.setRepository(tmpDir);// ָ���ϴ��ļ�����ʱĿ¼
					dff.setSizeThreshold(1024 * 4);// ָ�����ڴ��л������ݴ�С,��λΪbyte
					ServletFileUpload sfu = new ServletFileUpload(dff);// �����ö���
					sfu.setFileSizeMax(1024 * 1024 * 10);// ָ�������ϴ��ļ������ߴ�
					sfu.setSizeMax(1024 * 1024 * 10);// ָ��һ���ϴ�����ļ����ܳߴ�
					FileItemIterator fii = sfu.getItemIterator(req);// ����request
																	// ����,������FileItemIterator����
					while (fii.hasNext()) {
						FileItemStream fis = fii.next();// �Ӽ����л��һ���ļ���
						if (!fis.isFormField() && fis.getName().length() > 0) {// ���˵����з��ļ���

							BufferedInputStream in = new BufferedInputStream(
									fis.openStream());// ����ļ�������
							String fileName = "data"
									+ UUID.randomUUID().toString() + ".tmp";
							BufferedOutputStream out = new BufferedOutputStream(
									new FileOutputStream(new File(tmpDir
											+ fileName)));// ����ļ������
							Streams.copy(in, out, true);// ��ʼ���ļ�д����ָ�����ϴ��ļ���

							List<ExcelColumn> excelColumns = new ArrayList<ExcelColumn>();
							excelColumns.add(new ExcelColumn(0, "city", "����"));
							excelColumns.add(new ExcelColumn(1, "product", "��Ʒ"));
							excelColumns.add(new ExcelColumn(2, "num", "����",Cell.CELL_TYPE_STRING));
							excelColumns.add(new ExcelColumn(3, "salesman","����Ա"));
							excelColumns.add(new ExcelColumn(4, "remark", "��ע"));
							for (int i = 0; i < excelColumns.size(); i++) {
								System.out.println("excelColumns===="
										+ excelColumns.get(i).toString());
							}

							/*
							 * // ��Ҫ����ת���ĵ�Ԫ Map<String, Map>
							 * excelColumnsConvertMap = new HashMap<String,
							 * Map>(); Map<String, Integer> isReceive = new
							 * HashMap<String, Integer>(); isReceive.put("��",
							 * 1); isReceive.put("��", 0);
							 * excelColumnsConvertMap.put("isReceive",
							 * isReceive); Map<String, Integer> orderType = new
							 * HashMap<String, Integer>(); orderType.put("�¶���",
							 * 1); orderType.put("��������", 2);
							 * excelColumnsConvertMap.put("orderType",
							 * orderType);
							 */

							File sourceFile = new File(tmpDir + fileName);
							ExcelHead head = new ExcelHead();
							head.setRowCount(1); // ͷ��ռ����
							head.setColumns(excelColumns); // �еĶ���
							// head.setColumnsConvertMap(excelColumnsConvertMap);
							// �е�ת��

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
