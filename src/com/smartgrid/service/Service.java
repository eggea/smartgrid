package com.smartgrid.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.smartgrid.model.dao.MeteringDao;
import com.smartgrid.model.vo.MeteringVo;

@Path("/")
public class Service {

	@Path("/getmetering")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public MeteringVo getMetering(
			@QueryParam("data") String data,
			@QueryParam("tipo") String tipo) {			
		
			MeteringDao mtdao = new MeteringDao();
			MeteringVo mt = new MeteringVo();
			
			mt = mtdao.getMeteringDaily(5, "2014-03-01");
			return mt; 			

	}

	@Path("/teste")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public MeteringVo teste() {
		MeteringVo mt = new MeteringVo();
		mt.setDeviceName("smartplug");
		List<Float> flist = new ArrayList<Float>();
		flist.add(10F);
		flist.add(20F);
		flist.add(30F);
		mt.setConsumptionValue(flist);
		return mt;
	}
}
