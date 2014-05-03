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
import com.smartgrid.model.vo.MeteringVo;

/*
 * Caminho completo:
 * http://localhost:8080/smartgrid/rest/
 */
@Path("/")
public class Service {

	// RETORNA TODOS OS VALORES DA TABELA DE UM ID (DEMORA)
	// http://localhost:8080/smartgrid/rest/getmeteringall?id=1
	@Path("/getmeteringall")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<MeteringVo> getMeteringAll(
			@QueryParam("id") Integer id) {
		SmartgridDao sgDao = new SmartgridDao();
		List<MeteringVo> muList = sgDao.getMeteringAll(id);
		return muList;
	}

	// RETORNA 24 VALORES DIARIO
	// http://localhost:8080/smartgrid/rest/getmeteringdaily?id=1&date=2014-03-01
	@Path("/getmeteringdaily")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<MeteringVo> getMeteringDaily(
			@QueryParam("id") Integer id,
			@QueryParam("date") String date) {
			SmartgridDao sgDao = new SmartgridDao();
			List<MeteringVo> muList = new ArrayList<MeteringVo>();
			System.out.println("ID:" + id);
			System.out.println("DATE: " + date);
			muList = sgDao.getMeteringDaily(id, date);
		return muList;
	}
	
	// RETORNA 7 VALORES 
	// http://localhost:8080/smartgrid/rest/getmeteringweekly?id=1&date=2014-03-01
	@Path("/getmeteringweekly")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<MeteringVo> getMeteringWeekly(
			@QueryParam("id") Integer id,
			@QueryParam("date") String date) {
		SmartgridDao sgDao = new SmartgridDao();
		List<MeteringVo> muList = new ArrayList<MeteringVo>();
		System.out.println("ID:" + id);
		System.out.println("DATE: " + date);
		muList = sgDao.getMeteringWeekly(id, date);
	return muList;
	}

	// RETORNA 30 VALORES
	// http://localhost:8080/smartgrid/rest/getmeteringmonthly?id=1&date=2014-03-01
	@Path("/getmeteringmonthly")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<MeteringVo> getMeteringMonthly(
			@QueryParam("id") Integer id,
			@QueryParam("date") String date) {
		SmartgridDao sgDao = new SmartgridDao();
		List<MeteringVo> muList = new ArrayList<MeteringVo>();
		System.out.println("ID:" + id);
		System.out.println("DATE: " + date);
		muList = sgDao.getMeteringMonthly(id, date);
	return muList;
	}
	
	// RETORNA UM OBJETO COM VALORES INSTANTANEOS (ULTIMOS 5 MINUTOS)
	// http://localhost:8080/smartgrid/rest/getmeteringnow?id=1
	@Path("/getmeteringnow")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<MeteringVo> getMeteringNow(
			@QueryParam("id") Integer id) {
		SmartgridDao sgDao = new SmartgridDao();
		List<MeteringVo> muList = new ArrayList<MeteringVo>();
		muList = sgDao.getMeteringNow(id);
	return muList;
	}

	// RETORNA LISTA DE DEVICES
	// http://localhost:8080/smartgrid/rest/getdevicelist
	@Path("/getdevicelist")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<DeviceVo> getDeviceList() {

		SmartgridDao sgDao = new SmartgridDao();
		List<DeviceVo> devList = new ArrayList<DeviceVo>();
		devList = sgDao.listDevices();
		return devList;
	}

	// RETORNA A TARIFA PARA UM DETERMINADO DIA - HORA
	// http://localhost:8080/smartgrid/rest/getprice?datetime=2014-03-01 18:00
	@Path("/getprice")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Float getPrice(
			@QueryParam("datetime") String datetime){
		System.out.println("DATETIME: "+ datetime);
		SmartgridDao sgDao = new SmartgridDao();
		Float price = sgDao.getPrice(datetime);
		return price;
	}
	

}
