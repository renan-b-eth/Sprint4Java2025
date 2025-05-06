package br.com.fiap.vendasms.external_interface.cep;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value="cepClient", url="https://viacep.com.br/ws"  )
public interface CepApi {

    @RequestMapping(method = RequestMethod.GET, value = "/{cep}/json/")
    CepDetails get(@PathVariable("cep") String cep);
}
