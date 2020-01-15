package CONTROLLER;

import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.pentaho.reporting.engine.classic.core.ClassicEngineBoot;
import org.pentaho.reporting.engine.classic.core.MasterReport;
import org.pentaho.reporting.engine.classic.core.modules.output.pageable.pdf.PdfReportUtil;
import org.pentaho.reporting.libraries.resourceloader.Resource;
import org.pentaho.reporting.libraries.resourceloader.ResourceManager;

/**
 * Servlet implementation class CONTROLDATOS
 */
@WebServlet("/CONTROLDATOS")
public class CONTROLDATOS extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		ClassicEngineBoot.getInstance().start();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CONTROLDATOS() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			long inicio = System.currentTimeMillis();
			ResourceManager manager = new ResourceManager();
			manager.registerDefaults();
			String ruta = "file:" + this.getServletContext().getRealPath("Primer_reporte.prpt");
			Resource res = manager.createDirectly(new URL(ruta), MasterReport.class);
			MasterReport reporte = (MasterReport) res.getResource();
			response.setContentType("application/pdf");
			PdfReportUtil.createPDF(reporte, response.getOutputStream());
			long fin = System.currentTimeMillis();
			long dif = fin - inicio;
			System.out.println("time=" + dif + " miliseconds");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
