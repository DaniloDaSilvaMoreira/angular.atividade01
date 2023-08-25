package br.unitins.topicos2.resource;

import java.util.List;

import org.jboss.logging.Logger;

import br.unitins.topicos2.application.Result;
import br.unitins.topicos2.dto.FuncionarioDTO;
import br.unitins.topicos2.dto.FuncionarioResponseDTO;
import br.unitins.topicos2.service.FuncionarioService;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/funcionarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FuncionarioResource {

    @Inject
    FuncionarioService funcionarioService;

    private static final Logger LOG = Logger.getLogger(FuncionarioResource.class);

    @GET
    public List<FuncionarioResponseDTO> getAll() {
        LOG.info("Buscando todos os funcionarios.");
        LOG.debug("ERRO DE DEBUG.");
        return funcionarioService.getAll();
    }

    @GET
    @Path("/{id}")
    public FuncionarioResponseDTO findById(@PathParam("id") Long id) {
        return funcionarioService.findById(id);
    }

    @POST
    public Response insert(FuncionarioDTO dto) {
        LOG.infof("Inserindo um funcionario: %s", dto.nome());
        Result result = null;
        try {
            FuncionarioResponseDTO funcionario = funcionarioService.create(dto);
            LOG.infof("Funcionario (%d) criado com sucesso.", funcionario.id());
            return Response.status(Status.CREATED).entity(funcionario).build();
        } catch(ConstraintViolationException e) {
            LOG.error("Erro ao incluir um funcionario.");
            LOG.debug(e.getMessage());
            result = new Result(e.getConstraintViolations());
        } catch (Exception e) {
            LOG.fatal("Erro sem identificacao: " + e.getMessage());
            result = new Result(e.getMessage(), false);
        }
        return Response.status(Status.NOT_FOUND).entity(result).build();

    }    

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, FuncionarioDTO dto) {
        try {
            FuncionarioResponseDTO funcionario = funcionarioService.update(id, dto);
            return Response.ok(funcionario).build();
        } catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }      
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        funcionarioService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @Path("/count")
    public long count(){
        return funcionarioService.count();
    }

    @GET
    @Path("/search/{nome}")
    public List<FuncionarioResponseDTO> search(@PathParam("nome") String nome){
        return funcionarioService.findByNome(nome);
        
    }
}
