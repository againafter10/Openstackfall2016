package cmpe283.dashboard;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.openstack4j.model.compute.*;
import org.openstack4j.model.compute.ext.*;

@Path("/vtDashboard")
public class MyResource {
	
	OpenStackNova keystone = new OpenStackNova();
	NovaServices nova = new NovaServices();
    /** Method processing HTTP GET requests, producing "text/plain" MIME media
     * type.
     * @return String that will be send back as a response of type "text/plain".
     */
   
    @GET
	@Path("/nova/getAllServer")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllServer(){
    	List<? extends Server> servers = (List< ? extends Server>) nova.getAllServer();
    	GenericEntity<List<? extends Server>> entity = new GenericEntity<List<? extends Server>>(servers) {};
		return Response.status(Status.OK).entity(entity).build();
    }

    @GET
	@Path("/nova/getServerById/{serverID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getServerById(@PathParam("serverID") String serverID){
    	Server server = (Server) nova.getServerById(serverID);
    	GenericEntity<Server> entity = new GenericEntity<Server>(server){};
    	return Response.status(Status.OK).entity(entity).build();    
    }

    @GET
	@Path("/nova/ServerAction/{serverID}{action}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ServerAction(@PathParam("serverID") String serverID, @PathParam("action") String action){
    	nova.ServerAction(serverID, action);
    	return Response.status(Status.OK).entity("Server with ID : " + serverID + " has" + action).build();
    }

    @GET
	@Path("/nova/RebootServer/{serverID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response RebootServer(@PathParam("serverID") String serverID){
    	nova.RebootServer(serverID);
    	return Response.status(Status.OK).entity("Server with ID : " + serverID + " is rebooting").build();
    }
    
    @GET
	@Path("/nova/DeleteServer/{serverID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ServerAction(@PathParam("serverID") String serverId){
    	nova.deleteServer(serverId);
    	return Response.status(Status.OK).entity("Server with ID : " + serverId + " has delete").build();
    }
    
    @GET
	@Path("/nova/flavors")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFlavors(){
    	List<? extends Flavor> flavorList = (List< ? extends Flavor>) nova.getAllFlavors();
    	GenericEntity<List<? extends Flavor>> entity = new GenericEntity<List<? extends Flavor>>(flavorList) {};
		return Response.status(Status.OK).entity(entity).build();
    }
    
    @GET
	@Path("/nova/images")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getImages(){
    	List<? extends Image> flavorList = (List< ? extends Image>) nova.getAllImages();
    	GenericEntity<List<? extends Image>> entity = new GenericEntity<List<? extends Image>>(flavorList) {};
		return Response.status(Status.OK).entity(entity).build();
    }
    
    @GET
	@Path("/nova/allkeypairs")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getKeyPairsAll(){
		List<? extends Keypair> kps = (List<? extends Keypair>) nova.getAllKeyPairs();
    	GenericEntity<List<? extends Keypair>> entity = new GenericEntity<List<? extends Keypair>>(kps) {};
		return Response.status(Status.OK).entity(entity).build();
    }
    
    @GET
    @Path("/nova/allSecGroup")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSecGroupAll(){
    	List<? extends SecGroupExtension> sg = (List<? extends SecGroupExtension>) nova.getAllSecGroups();
    	GenericEntity<List<? extends SecGroupExtension>> entity = new GenericEntity<List<? extends SecGroupExtension>>(sg){};
    	return Response.status(Status.OK).entity(entity).build();
    }
    
    @GET
    @Path("/nova/getSecGroupByGroupID/{secGroupID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSecGroupByGroupID(@PathParam("secGroupID") String secgroupid){
    	SecGroupExtension group = (SecGroupExtension) nova.getSecGroupByGroupID(secgroupid);
    	GenericEntity<SecGroupExtension> entity = new GenericEntity<SecGroupExtension>(group){};
    	return Response.status(Status.OK).entity(entity).build();
    }
    
    @GET
    @Path("/nova/getSecGroupByServerID/{secGroupServerID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSecGroupByServerID(@PathParam("secGroupServerID") String id){
    	List<? extends SecGroupExtension> sg = (List<? extends SecGroupExtension>) nova.getSecGroupByServerID(id);
    	GenericEntity<List<? extends SecGroupExtension>> entity = new GenericEntity<List<? extends SecGroupExtension>>(sg){};
    	return Response.status(Status.OK).entity(entity).build();
    }
    
    @GET
    @Path("/nova/getTenantUsages")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTenantUsages(){
    	List<? extends SimpleTenantUsage> tenantUsages = (List<? extends SimpleTenantUsage>) nova.getTenantUsages();
    	GenericEntity<List<? extends SimpleTenantUsage>> entity = new GenericEntity<List<? extends SimpleTenantUsage>>(tenantUsages){};
    	return Response.status(Status.OK).entity(entity).build();
    }

    @GET
    @Path("/nova/getTenantUsagesById/{tenantUsageByID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTenantUsagesById(@PathParam("tenantUsageByID") String tenantid){
    	SimpleTenantUsage usage = (SimpleTenantUsage) nova.getTenantUsagesById(tenantid);
    	GenericEntity<SimpleTenantUsage> entity = new GenericEntity<SimpleTenantUsage>(usage){};
    	return Response.status(Status.OK).entity(entity).build();
    }
    
    @GET
    @Path("/nova/getRegDomainList")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRegDomainList(){
    	List<? extends DomainEntry> domains = (List<? extends DomainEntry>) nova.getRegDomainList();
    	GenericEntity<List<? extends DomainEntry>> entity = new GenericEntity<List<? extends DomainEntry>>(domains){};
    	return Response.status(Status.OK).entity(entity).build();
    }

    @GET
    @Path("/nova/getUniqueDNS/{domain}{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUniqueDNS(@PathParam("domain") String domain, @PathParam("name") String name){
    	List<? extends DNSEntry> entries = (List<? extends DNSEntry>) nova.getUniqueDNS(domain, name);
    	GenericEntity<List<? extends DNSEntry>> entity = new GenericEntity<List<? extends DNSEntry>>(entries){};
    	return Response.status(Status.OK).entity(entity).build();
    }    
    
    @GET
    @Path("/nova/getDNSlistbyIP/{domain}{ip}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDNSlistbyIP(@PathParam("domain") String domain, @PathParam("ip") String ip){
    	List<? extends DNSEntry> entries = (List<? extends DNSEntry>) nova.getUniqueDNS(domain, ip);
    	GenericEntity<List<? extends DNSEntry>> entity = new GenericEntity<List<? extends DNSEntry>>(entries){};
    	return Response.status(Status.OK).entity(entity).build();
    }    
}
