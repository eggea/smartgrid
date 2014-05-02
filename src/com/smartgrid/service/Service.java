package com.smartgrid.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.smartgrid.model.dao.SmartgridDao;
import com.smartgrid.model.vo.ActionVo;
import com.smartgrid.model.vo.DeviceVo;
import com.smartgrid.model.vo.MeteringVo;

/*
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

	// RETORNA 24 VALORES DIARIO ACUMULADO
	// http://localhost:8080/smartgrid/rest/getmeteringdailyacc?id=1&date=2014-03-01
	@Path("/getmeteringdailyacc")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<MeteringVo> getMeteringDailyAcc(
			@QueryParam("id") Integer id,
			@QueryParam("date") String date) {
			SmartgridDao sgDao = new SmartgridDao();
			List<MeteringVo> muList = new ArrayList<MeteringVo>();
			System.out.println("ID:" + id);
			System.out.println("DATE: " + date);
			muList = sgDao.getMeteringDailyAcc(id, date);
		return muList;
	}	
	
	
	
	// RETORNA 7 VALORES (SEMANAL)
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
	
	// RETORNA 7 VALORES - SEMANAL ACUMULADO
	// http://localhost:8080/smartgrid/rest/getmeteringweeklyacc?id=1&date=2014-03-01
	@Path("/getmeteringweeklyacc")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<MeteringVo> getMeteringWeeklyAcc(
			@QueryParam("id") Integer id,
			@QueryParam("date") String date) {
		SmartgridDao sgDao = new SmartgridDao();
		List<MeteringVo> muList = new ArrayList<MeteringVo>();
		System.out.println("ID:" + id);
		System.out.println("DATE: " + date);
		muList = sgDao.getMeteringWeeklyAcc(id, date);
	return muList;
	}	

	// RETORNA 30 VALORES - MENSAL
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
	// RETORNA 30 VALORES - MENSAL ACUMULADO
	// http://localhost:8080/smartgrid/rest/getmeteringmonthlyacc?id=1&date=2014-03-01
	@Path("/getmeteringmonthlyacc")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<MeteringVo> getMeteringMonthlyAcc(
			@QueryParam("id") Integer id,
			@QueryParam("date") String date) {
		SmartgridDao sgDao = new SmartgridDao();
		List<MeteringVo> muList = new ArrayList<MeteringVo>();
		System.out.println("ID:" + id);
		System.out.println("DATE: " + date);
		muList = sgDao.getMeteringMonthlyAcc(id, date);
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
	
	// ADICIONA DEVICE
	// http://localhost:8080/smartgrid/rest/adddevice?name=smartplug&description=teste%20de%20&devicetype=SP
	@Path("/adddevice")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDeviceList(
			@QueryParam("name") String name,
			@QueryParam("description") String description,
			@QueryParam("devicetype") String deviceType) {

		SmartgridDao sgDao = new SmartgridDao();
		sgDao.addDevice(name, description, deviceType);
		return Response.ok("Device adicionado!").build(); // Responde 200 ok
	}	
	
	// EDITAR DEVICE
	// http://localhost:8080/smartgrid/rest/updatedevice?name=smartplug&description=teste%20de%20&devicetype=SP
	@Path("/updatedevice")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDeviceList(
			@QueryParam("id") Integer id,
			@QueryParam("name") String name,
			@QueryParam("description") String description,
			@QueryParam("devicetype") String deviceType) {

		SmartgridDao sgDao = new SmartgridDao();
		int http_code = sgDao.updateDevice(id, name, description, deviceType);
		
		if (http_code==500){
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		return Response.ok("Device editado!").build(); // Responde 200 ok
	}	
	
	// DELETE DEVICE
	@Path("/deletedevice")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDeviceList(
			@QueryParam("id") Integer id ) {

		SmartgridDao sgDao = new SmartgridDao();
		int http_code = sgDao.deleteDevice(id);
				 
		if (http_code==500){
			return Response.status(500).entity("Server was not able to process your request").build();
		}			
		return Response.ok("Device " + id + " atualizado!").build(); // Responde 200 ok
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
		
	// LISTA OS AGENDAMENTOS
	//
	// http://localhost:8080/smartgrid/rest/listactions
	//
	@Path("/listactions")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ActionVo> setAction(){	
		
		SmartgridDao sgDao = new SmartgridDao();
		List<ActionVo> actList = sgDao.listActions();		
		return actList;
	}
	
	
	// INSERE UM AGENDAMENTO NA TABELA 
	// http://localhost:8080/smartgrid/rest/addaction?id=1&date=2014-04-01&description=teste&type=on
	@Path("/addaction")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response setAction(
			@QueryParam("id") Integer id,
			@QueryParam("date") String date,
			@QueryParam("description") String description,
			@QueryParam("type") String type){
		
		SmartgridDao sgDao = new SmartgridDao();
		int http_code = sgDao.addAction(id, date, description, type);
		if (http_code==500){
			return Response.status(500).entity("Server was not able to process your request").build();
		}			
		return Response.ok("Agendamento inserido!").build(); // Responde 200 ok		

	}

	// EDITA AGENDAMENTO NA TABELA
	// http://localhost:8080/smartgrid/rest/updateaction?idaction=4&iddevice=3&date=2014-04-01&description=teste&type=OFF
	@Path("/updateaction")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateAction(
			@QueryParam("idaction") Integer idAction,
			@QueryParam("iddevice") Integer idDevice,			
			@QueryParam("date") String date,
			@QueryParam("description") String description,
			@QueryParam("type") String type){
		
		SmartgridDao sgDao = new SmartgridDao();
		int http_code = sgDao.updateAction(idAction, idDevice, date, description, type);

		if (http_code==500){
			return Response.status(500).entity("Server was not able to process your request").build();
		}			
		return Response.ok("Agendamento " + idAction + " atualizado!").build(); // Responde 200 ok		
	}
	
	//REMOVE AGENDAMENTO
	// http://localhost:8080/smartgrid/rest/deleteaction?idaction=2
	@Path("/deleteaction")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAction(
			@QueryParam("idaction") Integer idAction){
		
		SmartgridDao sgDao = new SmartgridDao();
		int http_code = sgDao.deleteAction(idAction);
		if (http_code==500){
			return Response.status(500).entity("Server was not able to process your request").build();
		}			
		return Response.ok("Agendamento " + idAction + " apagado!").build(); // Responde 200 ok
	}	
		

}
