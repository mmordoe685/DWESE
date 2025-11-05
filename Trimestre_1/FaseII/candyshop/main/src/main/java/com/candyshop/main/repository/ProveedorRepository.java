package com.candyshop.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.candyshop.main.model.Proveedor;

public interface ProveedorRepository extends JpaRepository< Proveedor ,Long>{


    //Consultas JpaRepository 

    //filtrar por Nombre y email
    public List<Proveedor> findByNombreAndEmail(String nombre , String email);


    

   
}
