package com.smartgrid.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.smartgrid.model.dao.SmartgridDao;
import com.smartgrid.model.vo.DeviceVo;
import com.smartgrid.model.vo.MeteringUnit;
import com.smartgrid.model.vo.MeteringVo;

/*
 * Caminho completo:
 * http://localhost:8080/smartgrid/rest/
 */
@Path("/")
public class Service {
	// Retorna todos os valores da base de um determinado deviceid
	// http://localhost:8080/smartgrid/rest/getmetering?id=
	@Path("/getmeteringV2")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public MeteringVo getMeteringv2(@QueryParam("id") Integer id) {

		SmartgridDao sgDao = new SmartgridDao();
		MeteringVo mt = new MeteringVo();
		mt = sgDao.getMeteringv1(5);
		return mt;
	}

	@Path("/getmetering")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<MeteringUnit> getMetering(
			@QueryParam("id") Integer id) {

		SmartgridDao sgDao = new SmartgridDao();
		List<MeteringUnit> muList = sgDao.getMetering(5);
		return muList;
	}

	@Path("/getmeteringdaily")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<MeteringUnit> getMeteringDiario(
			@QueryParam("id") Integer id,
			@QueryParam("date") String date) {
			SmartgridDao sgDao = new SmartgridDao();
			List<MeteringUnit> muList = sgDao.getMetering(id);
			System.out.println("ID:" + id);
			System.out.println("DATE: " + date);
			muList = sgDao.getMeteringDaily(id, date);
		return muList;
	}

	@Path("/getmeteringsemanal")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public MeteringVo getMeteringSemanal(@QueryParam("id") Integer id,
			@QueryParam("dia") String data) {

		return null;
	}

	@Path("/getmeteringmensal")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public MeteringVo getMeteringMensal(@QueryParam("id") Integer id,
			@QueryParam("mes") String data) {

		return null;
	}

	// http://localhost:8080/smartgrid/rest/getdevicelist
	@Path("/getdevicelist")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<DeviceVo> getDevices() {

		SmartgridDao sgDao = new SmartgridDao();
		List<DeviceVo> devList = new ArrayList<DeviceVo>();
		devList = sgDao.listDevices();
		return devList;
	}

	// http://localhost:8080/smartgrid/rest/getprice
	@Path("/getprice")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Float getMeteringv2(@QueryParam("datetime") String datetime){
		System.out.println("DATETIME: "+ datetime);
		SmartgridDao sgDao = new SmartgridDao();
		Float price = sgDao.getPrice(datetime);
		return price;
	}
}
