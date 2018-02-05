package com.example.k11.k.Utilidades;

/**
 * Created by k11 on 25/01/18.
 */

public class Constantes {

    //TABLA DE USUARIO
    public static final String TABLA_NOMBRE_USUARIO = "USUARIO";
    public static final String TABLA_USUARIO_ID = "ID";
    public static final String TABLA_USUARIO_NOMBRE = "NOMBRE";
    public static final String TABLA_USUARIO_CONTRASEÑA = "CONTRASENA";
    public static final String TABLA_USUARIO_ESTADO_CON = "ESTADO_CON";

    public static final String CREATE_TABLA_USUARIO =
            "CREATE TABLE " + TABLA_NOMBRE_USUARIO + " (" +
                    TABLA_USUARIO_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                    TABLA_USUARIO_NOMBRE + " TEXT NOT NULL UNIQUE, " +
                    TABLA_USUARIO_ESTADO_CON + " TEXT DEFAULT 'Inactivo',"+
                    TABLA_USUARIO_CONTRASEÑA + " TEXT NOT NULL)";

    //TABLA DE ENCARGO
    public static final String TABLA_NOMBRE_ENCARGO = "ENCARGO";
    public static final String TABLA_ENCARGO_ID = "ID";
    public static final String TABLA_ENCARGO_NOM_ENCARGO = "NOM_ENCARGO";
    public static final String TABLA_ENCARGO_CANTIDAD = "CANTIDAD";
    public static final String TABLA_ENCARGO_DESCRIPCION = "DESCRIPCION";
    public static final String TABLA_ENCARGO_NOM_CLIENTE = "NOM_CLIENTE";
    public static final String TABLA_ENCARGO_COSTO_ENCARGO = "COSTO_ENCARGO";
    public static final String TABLA_ENCARGO_ESTADO_COMPRA = "ESTADO_COMPRA";
    public static final String TABLA_ENCARGO_CEL_CLIENTE = "CEL_CLIENTE";
    public static final String TABLA_ENCARGO_ID_USUARIO = "ID_USUARIO";
    public static final String CREATE_TABLA_ENCARGO =
            "CREATE TABLE " + TABLA_NOMBRE_ENCARGO + " (" +
                    TABLA_ENCARGO_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                    TABLA_ENCARGO_NOM_ENCARGO + " TEXT NOT NULL, " +
                    TABLA_ENCARGO_CANTIDAD + " INTEGER NOT NULL, " +
                    TABLA_ENCARGO_DESCRIPCION + " TEXT DEFAULT 'No hay descripción', " +
                    TABLA_ENCARGO_NOM_CLIENTE + " TEXT NOT NULL, " +
                    TABLA_ENCARGO_COSTO_ENCARGO + " INTEGER DEFAULT 0, " +
                    TABLA_ENCARGO_ESTADO_COMPRA + " TEXT DEFAULT 'Pendiente', " +
                    TABLA_ENCARGO_CEL_CLIENTE + " TEXT DEFAULT 'Vacio' ," +
                    TABLA_ENCARGO_ID_USUARIO + " INTEGER NOT NULL, " +
                    "FOREIGN KEY(`ID_USUARIO`) REFERENCES USUARIO)";
}
