package org.example.persistencia.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.example.persistencia.model.Cuenta;
import org.example.persistencia.service.CuentaService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/cuenta")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @Secured({ "USER" })
    @PostMapping("/{id}/retirar")
    @Transactional
    public BigDecimal retirar(@PathVariable Long id, @RequestBody BigDecimal cantidad) {
        return cuentaService.retirar(id, cantidad);
    }

    @Secured({ "USER" })
    @PostMapping("/{id}/abonar")
    @Transactional
    public BigDecimal abonar(@PathVariable Long id, @RequestBody BigDecimal cantidad) {
        return cuentaService.abonar(id, cantidad);
    }

    @Secured({ "USER" })
    @GetMapping("/{id}/saldo")
    @Transactional
    public BigDecimal saldo(@PathVariable Long id) {
        return cuentaService.saldo(id);
    }

    @Secured({ "ADMIN" })
    @GetMapping("/{id}")
    @Transactional
    public Cuenta cuenta(@PathVariable Long id) {
        return cuentaService.cuenta(id);
    }

    @Secured({ "ADMIN" })
    @GetMapping("")
    public List<Cuenta> all() {
        return cuentaService.all();
    }
}