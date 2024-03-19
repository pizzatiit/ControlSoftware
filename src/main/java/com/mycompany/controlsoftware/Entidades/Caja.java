/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controlsoftware.Entidades;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author Usuario
 */
public record Caja( int id,
    int idusuario,
    String fechaapertura,
    String fechacierre,
    BigDecimal valorinicial,
    BigDecimal valoractual,
    BigDecimal valorfinal,
    int estado,
    int cia){ 

}
