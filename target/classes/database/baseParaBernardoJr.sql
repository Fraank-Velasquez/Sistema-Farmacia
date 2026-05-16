--
-- PostgreSQL database dump
--

\restrict N0A4FYxDVvMkjla5XT7CwkqdWNDNEtmS7Y4qAqatQ6wIlwd9itxYCLqDJbRE2VA

-- Dumped from database version 17.6
-- Dumped by pg_dump version 17.6

-- Started on 2026-05-15 23:24:40

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 266 (class 1255 OID 65818)
-- Name: agregar_categoria(character varying, character varying); Type: PROCEDURE; Schema: public; Owner: fvelasquezl
--

CREATE PROCEDURE public.agregar_categoria(IN inombre character varying, IN idescripcion character varying)
    LANGUAGE plpgsql
    AS $$
begin
    insert into categorias(nombre, descripcion)
    values (iNombre, iDescripcion);
end;
$$;


ALTER PROCEDURE public.agregar_categoria(IN inombre character varying, IN idescripcion character varying) OWNER TO fvelasquezl;

--
-- TOC entry 270 (class 1255 OID 65822)
-- Name: agregar_cliente(character varying, character varying, character varying, character varying, character varying); Type: PROCEDURE; Schema: public; Owner: fvelasquezl
--

CREATE PROCEDURE public.agregar_cliente(IN idni character varying, IN inombres character varying, IN iapellidos character varying, IN itelefono character varying, IN iemail character varying)
    LANGUAGE plpgsql
    AS $$
begin
    insert into clientes(dni, nombres, apellidos, telefono, email)
    values (iDni, iNombres, iApellidos, iTelefono, iEmail);
end;
$$;


ALTER PROCEDURE public.agregar_cliente(IN idni character varying, IN inombres character varying, IN iapellidos character varying, IN itelefono character varying, IN iemail character varying) OWNER TO fvelasquezl;

--
-- TOC entry 275 (class 1255 OID 65826)
-- Name: agregar_compra(integer, integer, character varying, numeric, numeric, numeric, date, character varying); Type: PROCEDURE; Schema: public; Owner: fvelasquezl
--

CREATE PROCEDURE public.agregar_compra(IN iid_proveedor integer, IN iid_usuario integer, IN inumero_factura character varying, IN isubtotal numeric, IN iimpuesto numeric, IN itotal numeric, IN ifecha_compra date, IN iobservaciones character varying)
    LANGUAGE plpgsql
    AS $$
begin
    insert into compras(
        id_proveedor, id_usuario, numero_factura,
        subtotal, impuesto, total,
        fecha_compra, observaciones
    )
    values (
        iId_proveedor, iId_usuario, iNumero_factura,
        iSubtotal, iImpuesto, iTotal,
        iFecha_compra, iObservaciones
    );
end;
$$;


ALTER PROCEDURE public.agregar_compra(IN iid_proveedor integer, IN iid_usuario integer, IN inumero_factura character varying, IN isubtotal numeric, IN iimpuesto numeric, IN itotal numeric, IN ifecha_compra date, IN iobservaciones character varying) OWNER TO fvelasquezl;

--
-- TOC entry 277 (class 1255 OID 65828)
-- Name: agregar_empresa(character varying, character varying, character varying, character varying, character varying, character varying, character varying); Type: PROCEDURE; Schema: public; Owner: fvelasquezl
--

CREATE PROCEDURE public.agregar_empresa(IN inombre character varying, IN itelefono character varying, IN iemail character varying, IN idireccion character varying, IN iciudad character varying, IN iruc character varying, IN itipo_empresa character varying)
    LANGUAGE plpgsql
    AS $$
begin
    insert into empresa(nombre, telefono,email,direccion,ciudad,ruc,tipo_empresa)
    values (iNombre, iTelefono,iEmail,iDireccion,iCiudad,iRuc,iTipo_empresa);
end;
$$;


ALTER PROCEDURE public.agregar_empresa(IN inombre character varying, IN itelefono character varying, IN iemail character varying, IN idireccion character varying, IN iciudad character varying, IN iruc character varying, IN itipo_empresa character varying) OWNER TO fvelasquezl;

--
-- TOC entry 292 (class 1255 OID 65833)
-- Name: agregar_lote(integer, integer, character varying, date, date, numeric, integer); Type: PROCEDURE; Schema: public; Owner: fvelasquezl
--

CREATE PROCEDURE public.agregar_lote(IN iidproducto integer, IN iid_empresa_proveedor integer, IN iid_numero_lote character varying, IN ifecha_fabricacion date, IN ifecha_vencimiento date, IN iprecio_compra numeric, IN icantidad_inicial integer)
    LANGUAGE plpgsql
    AS $$
begin
    insert into lotes(id_producto, id_empresa_proveedor, numero_lote,
	fecha_fabricacion,fecha_vencimiento,precio_compra,cantidad_inicial)
    values (iId_producto, iId_empresa_proveedor, iId_numero_lote, 
	iFecha_fabricacion, iFecha_vencimiento,iPrecio_compra,iCantidad_inicial);
end;
$$;


ALTER PROCEDURE public.agregar_lote(IN iidproducto integer, IN iid_empresa_proveedor integer, IN iid_numero_lote character varying, IN ifecha_fabricacion date, IN ifecha_vencimiento date, IN iprecio_compra numeric, IN icantidad_inicial integer) OWNER TO fvelasquezl;

--
-- TOC entry 258 (class 1255 OID 57815)
-- Name: agregar_productos(character varying, character varying, integer, integer, numeric, integer, integer); Type: PROCEDURE; Schema: public; Owner: fvelasquezl
--

CREATE PROCEDURE public.agregar_productos(IN inombre character varying, IN idescripcion character varying, IN iid_categoria integer, IN iid_empresa_fabricante integer, IN iprecio_venta numeric, IN istock_actual integer, IN istock_minimo integer)
    LANGUAGE plpgsql
    AS $$ 
begin 

insert into productos(nombre,descripcion,id_categoria,id_empresa_fabricante,
precio_venta,stock_actual,stock_minimo)
values (iNombre,iDescripcion,iId_categoria,iId_empresa_fabricante,iPrecio_venta,
iStock_actual,iStock_minimo);
end;
$$;


ALTER PROCEDURE public.agregar_productos(IN inombre character varying, IN idescripcion character varying, IN iid_categoria integer, IN iid_empresa_fabricante integer, IN iprecio_venta numeric, IN istock_actual integer, IN istock_minimo integer) OWNER TO fvelasquezl;

--
-- TOC entry 259 (class 1255 OID 65834)
-- Name: agregar_promocion(character varying, text, numeric, date, date); Type: PROCEDURE; Schema: public; Owner: fvelasquezl
--

CREATE PROCEDURE public.agregar_promocion(IN inombre character varying, IN idescripcion text, IN idescuento numeric, IN ifecha_inicio date, IN ifecha_fin date)
    LANGUAGE plpgsql
    AS $$
begin
    insert into promociones(nombre, descripcion, descuento, fecha_inicio, fecha_fin)
    values (iNombre, iDescripcion, iDescuento, iFecha_inicio, iFecha_fin);
end;
$$;


ALTER PROCEDURE public.agregar_promocion(IN inombre character varying, IN idescripcion text, IN idescuento numeric, IN ifecha_inicio date, IN ifecha_fin date) OWNER TO fvelasquezl;

--
-- TOC entry 293 (class 1255 OID 65841)
-- Name: agregar_venta(integer, integer, character varying, numeric, numeric, numeric, date, integer, character varying); Type: PROCEDURE; Schema: public; Owner: fvelasquezl
--

CREATE PROCEDURE public.agregar_venta(IN iid_cliente integer, IN iid_usuario integer, IN inumero_comprobante character varying, IN isubtotal numeric, IN idescuento numeric, IN itotal numeric, IN ifecha_venta date, IN iid_promocion integer, IN iobservaciones character varying)
    LANGUAGE plpgsql
    AS $$
begin
    insert into ventas(
        id_cliente, id_usuario, numero_comprobante,
        subtotal, descuento, total,
        fecha_venta, id_promocion, observaciones
    )
    values (
        iId_cliente, iId_usuario, iNumero_comprobante,
        iSubtotal, iDescuento, iTotal,
        iFecha_venta, iId_promocion, iObservaciones
    );
end;
$$;


ALTER PROCEDURE public.agregar_venta(IN iid_cliente integer, IN iid_usuario integer, IN inumero_comprobante character varying, IN isubtotal numeric, IN idescuento numeric, IN itotal numeric, IN ifecha_venta date, IN iid_promocion integer, IN iobservaciones character varying) OWNER TO fvelasquezl;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 220 (class 1259 OID 57639)
-- Name: categorias; Type: TABLE; Schema: public; Owner: fvelasquezl
--

CREATE TABLE public.categorias (
    id_categoria integer NOT NULL,
    nombre character varying(30) NOT NULL,
    descripcion character varying(255),
    fecha_creacion timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    activo boolean DEFAULT true
);


ALTER TABLE public.categorias OWNER TO fvelasquezl;

--
-- TOC entry 269 (class 1255 OID 65821)
-- Name: buscar_categoria(character varying); Type: FUNCTION; Schema: public; Owner: fvelasquezl
--

CREATE FUNCTION public.buscar_categoria(inombre character varying) RETURNS SETOF public.categorias
    LANGUAGE plpgsql
    AS $$
begin
    return query
    select *
    from categorias
    where nombre ilike '%' || iNombre || '%';
end;
$$;


ALTER FUNCTION public.buscar_categoria(inombre character varying) OWNER TO fvelasquezl;

--
-- TOC entry 226 (class 1259 OID 57689)
-- Name: clientes; Type: TABLE; Schema: public; Owner: fvelasquezl
--

CREATE TABLE public.clientes (
    id_cliente integer NOT NULL,
    dni character varying(8),
    nombres character varying(255) NOT NULL,
    apellidos character varying(255) NOT NULL,
    telefono character varying(15),
    email character varying(255),
    activo boolean DEFAULT true
);


ALTER TABLE public.clientes OWNER TO fvelasquezl;

--
-- TOC entry 274 (class 1255 OID 65825)
-- Name: buscar_cliente(character varying); Type: FUNCTION; Schema: public; Owner: fvelasquezl
--

CREATE FUNCTION public.buscar_cliente(inombre character varying) RETURNS SETOF public.clientes
    LANGUAGE plpgsql
    AS $$
begin
    return query
    select *
    from clientes
    where nombres ilike '%' || iNombre || '%'
       or apellidos ilike '%' || iNombre || '%';
end;
$$;


ALTER FUNCTION public.buscar_cliente(inombre character varying) OWNER TO fvelasquezl;

--
-- TOC entry 230 (class 1259 OID 57710)
-- Name: compras; Type: TABLE; Schema: public; Owner: fvelasquezl
--

CREATE TABLE public.compras (
    id_compra integer NOT NULL,
    id_proveedor integer NOT NULL,
    id_usuario integer NOT NULL,
    numero_factura character varying(100),
    subtotal numeric(10,2),
    impuesto numeric(10,2),
    total numeric(10,2),
    fecha_compra date,
    observaciones character varying(255),
    fecha_registro timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.compras OWNER TO fvelasquezl;

--
-- TOC entry 276 (class 1255 OID 65827)
-- Name: buscar_compra(character varying); Type: FUNCTION; Schema: public; Owner: fvelasquezl
--

CREATE FUNCTION public.buscar_compra(inumero_factura character varying) RETURNS SETOF public.compras
    LANGUAGE plpgsql
    AS $$
begin
    return query
    select *
    from compras
    where numero_factura ilike '%' || iNumero_factura || '%';
end;
$$;


ALTER FUNCTION public.buscar_compra(inumero_factura character varying) OWNER TO fvelasquezl;

--
-- TOC entry 218 (class 1259 OID 57628)
-- Name: empresa; Type: TABLE; Schema: public; Owner: fvelasquezl
--

CREATE TABLE public.empresa (
    id_empresa integer NOT NULL,
    nombre character varying(255) NOT NULL,
    telefono character varying(20),
    email character varying(255),
    direccion character varying(255),
    ciudad character varying(100),
    ruc character varying(50),
    tipo_empresa character varying(30) NOT NULL,
    fecha_registro timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    activo boolean DEFAULT true
);


ALTER TABLE public.empresa OWNER TO fvelasquezl;

--
-- TOC entry 291 (class 1255 OID 65832)
-- Name: buscar_empresa(character varying); Type: FUNCTION; Schema: public; Owner: fvelasquezl
--

CREATE FUNCTION public.buscar_empresa(inombre character varying) RETURNS SETOF public.empresa
    LANGUAGE plpgsql
    AS $$
begin
    return query
    select *
    from empresa
    where nombre ilike '%' || iNombre || '%';
end;
$$;


ALTER FUNCTION public.buscar_empresa(inombre character varying) OWNER TO fvelasquezl;

--
-- TOC entry 222 (class 1259 OID 57648)
-- Name: productos; Type: TABLE; Schema: public; Owner: fvelasquezl
--

CREATE TABLE public.productos (
    id_producto integer NOT NULL,
    nombre character varying(255) NOT NULL,
    descripcion character varying(255),
    id_categoria integer,
    id_empresa_fabricante integer,
    precio_venta numeric(10,2),
    stock_actual integer DEFAULT 0,
    stock_minimo integer DEFAULT 0,
    activo boolean DEFAULT true
);


ALTER TABLE public.productos OWNER TO fvelasquezl;

--
-- TOC entry 265 (class 1255 OID 57819)
-- Name: buscar_productos(character varying); Type: FUNCTION; Schema: public; Owner: fvelasquezl
--

CREATE FUNCTION public.buscar_productos(inombre character varying) RETURNS SETOF public.productos
    LANGUAGE plpgsql
    AS $$
begin
    return query
    select *
    from productos
    where nombre ilike '%' || inombre || '%';
end;
$$;


ALTER FUNCTION public.buscar_productos(inombre character varying) OWNER TO fvelasquezl;

--
-- TOC entry 238 (class 1259 OID 57782)
-- Name: promociones; Type: TABLE; Schema: public; Owner: fvelasquezl
--

CREATE TABLE public.promociones (
    id_promocion integer NOT NULL,
    nombre character varying(255),
    descripcion character varying(255),
    tipo_descuento character varying(50),
    valor_descuento numeric(10,2),
    fecha_inicio date,
    fecha_fin date,
    activo boolean DEFAULT true
);


ALTER TABLE public.promociones OWNER TO fvelasquezl;

--
-- TOC entry 262 (class 1255 OID 65837)
-- Name: buscar_promocion(character varying); Type: FUNCTION; Schema: public; Owner: fvelasquezl
--

CREATE FUNCTION public.buscar_promocion(inombre character varying) RETURNS SETOF public.promociones
    LANGUAGE plpgsql
    AS $$
begin
    return query
    select *
    from promociones
    where nombre ilike '%' || iNombre || '%';
end;
$$;


ALTER FUNCTION public.buscar_promocion(inombre character varying) OWNER TO fvelasquezl;

--
-- TOC entry 271 (class 1255 OID 65840)
-- Name: cambiar_contrasenia(integer, character varying); Type: PROCEDURE; Schema: public; Owner: fvelasquezl
--

CREATE PROCEDURE public.cambiar_contrasenia(IN iid_usuario integer, IN ipassword character varying)
    LANGUAGE plpgsql
    AS $$
begin
    update usuarios
    set password = iPassword
    where id_usuario = iId_usuario;
end;
$$;


ALTER PROCEDURE public.cambiar_contrasenia(IN iid_usuario integer, IN ipassword character varying) OWNER TO fvelasquezl;

--
-- TOC entry 267 (class 1255 OID 65819)
-- Name: editar_categoria(integer, character varying, character varying, boolean); Type: PROCEDURE; Schema: public; Owner: fvelasquezl
--

CREATE PROCEDURE public.editar_categoria(IN iid_categoria integer, IN inombre character varying, IN idescripcion character varying, IN iactivo boolean)
    LANGUAGE plpgsql
    AS $$
begin
    update categorias
    set nombre = iNombre,
        descripcion = iDescripcion,
        activo = iActivo
    where id_categoria = iId_categoria;
end;
$$;


ALTER PROCEDURE public.editar_categoria(IN iid_categoria integer, IN inombre character varying, IN idescripcion character varying, IN iactivo boolean) OWNER TO fvelasquezl;

--
-- TOC entry 272 (class 1255 OID 65823)
-- Name: editar_cliente(integer, character varying, character varying, character varying, character varying, character varying, boolean); Type: PROCEDURE; Schema: public; Owner: fvelasquezl
--

CREATE PROCEDURE public.editar_cliente(IN iid_cliente integer, IN idni character varying, IN inombres character varying, IN iapellidos character varying, IN itelefono character varying, IN iemail character varying, IN iactivo boolean)
    LANGUAGE plpgsql
    AS $$
begin
    update clientes
    set dni = iDni,
        nombres = iNombres,
        apellidos = iApellidos,
        telefono = iTelefono,
        email = iEmail,
        activo = iActivo
    where id_cliente = iId_cliente;
end;
$$;


ALTER PROCEDURE public.editar_cliente(IN iid_cliente integer, IN idni character varying, IN inombres character varying, IN iapellidos character varying, IN itelefono character varying, IN iemail character varying, IN iactivo boolean) OWNER TO fvelasquezl;

--
-- TOC entry 288 (class 1255 OID 65830)
-- Name: editar_empresa(character varying, character varying, character varying, character varying, character varying, character varying, character varying, integer); Type: PROCEDURE; Schema: public; Owner: fvelasquezl
--

CREATE PROCEDURE public.editar_empresa(IN inombre character varying, IN itelefono character varying, IN iemail character varying, IN idireccion character varying, IN iciudad character varying, IN iruc character varying, IN itipo_empresa character varying, IN iid_empresa integer)
    LANGUAGE plpgsql
    AS $$
begin
    update categorias
    set nombre = iNombre,
        telefono = iTelefono,
        email = iEmail,
		direccion = iDireccion,
        ciudad = iCiudad,
		ruc = iRuc,
        tipo_empresa = iTipo_empresa

    where id_empresa = iId_empresa;
end;
$$;


ALTER PROCEDURE public.editar_empresa(IN inombre character varying, IN itelefono character varying, IN iemail character varying, IN idireccion character varying, IN iciudad character varying, IN iruc character varying, IN itipo_empresa character varying, IN iid_empresa integer) OWNER TO fvelasquezl;

--
-- TOC entry 263 (class 1255 OID 57817)
-- Name: editar_productos(character varying, character varying, integer, integer, numeric, integer, integer, integer); Type: PROCEDURE; Schema: public; Owner: fvelasquezl
--

CREATE PROCEDURE public.editar_productos(IN inombre character varying, IN idescripcion character varying, IN iid_categoria integer, IN iid_empresa_fabricacion integer, IN iprecio_venta numeric, IN istock_actual integer, IN istock_minimo integer, IN iid_producto integer)
    LANGUAGE plpgsql
    AS $$ 
begin 
update productos set nombre=iNombre ,descripcion=iDescripcion , id_categoria=iId_categoria,
id_empresa_fabricacion= iId_empresa_fabricacion  , precio_venta=iPrecio_venta,
stock_actual=iStock_actual  ,stock_minimo =iStock_minimo
where id_producto = iId_producto;
end;
$$;


ALTER PROCEDURE public.editar_productos(IN inombre character varying, IN idescripcion character varying, IN iid_categoria integer, IN iid_empresa_fabricacion integer, IN iprecio_venta numeric, IN istock_actual integer, IN istock_minimo integer, IN iid_producto integer) OWNER TO fvelasquezl;

--
-- TOC entry 260 (class 1255 OID 65835)
-- Name: editar_promocion(integer, character varying, text, numeric, date, date, boolean); Type: PROCEDURE; Schema: public; Owner: fvelasquezl
--

CREATE PROCEDURE public.editar_promocion(IN iid_promocion integer, IN inombre character varying, IN idescripcion text, IN idescuento numeric, IN ifecha_inicio date, IN ifecha_fin date, IN iactivo boolean)
    LANGUAGE plpgsql
    AS $$
begin
    update promociones
    set nombre = iNombre,
        descripcion = iDescripcion,
        descuento = iDescuento,
        fecha_inicio = iFecha_inicio,
        fecha_fin = iFecha_fin,
        activo = iActivo
    where id_promocion = iId_promocion;
end;
$$;


ALTER PROCEDURE public.editar_promocion(IN iid_promocion integer, IN inombre character varying, IN idescripcion text, IN idescuento numeric, IN ifecha_inicio date, IN ifecha_fin date, IN iactivo boolean) OWNER TO fvelasquezl;

--
-- TOC entry 268 (class 1255 OID 65820)
-- Name: eliminar_categoria(integer); Type: PROCEDURE; Schema: public; Owner: fvelasquezl
--

CREATE PROCEDURE public.eliminar_categoria(IN iid_categoria integer)
    LANGUAGE plpgsql
    AS $$
begin
    update categorias
    set activo = false
    where id_categoria = iId_categoria;
end;
$$;


ALTER PROCEDURE public.eliminar_categoria(IN iid_categoria integer) OWNER TO fvelasquezl;

--
-- TOC entry 273 (class 1255 OID 65824)
-- Name: eliminar_cliente(integer); Type: PROCEDURE; Schema: public; Owner: fvelasquezl
--

CREATE PROCEDURE public.eliminar_cliente(IN iid_cliente integer)
    LANGUAGE plpgsql
    AS $$
begin
    update clientes
    set activo = false
    where id_cliente = iId_cliente;
end;
$$;


ALTER PROCEDURE public.eliminar_cliente(IN iid_cliente integer) OWNER TO fvelasquezl;

--
-- TOC entry 290 (class 1255 OID 65831)
-- Name: eliminar_empresa(integer); Type: PROCEDURE; Schema: public; Owner: fvelasquezl
--

CREATE PROCEDURE public.eliminar_empresa(IN iid_empresa integer)
    LANGUAGE plpgsql
    AS $$
begin
    update empresa
    set activo = false
    where id_empresa = iId_empresa;
end;
$$;


ALTER PROCEDURE public.eliminar_empresa(IN iid_empresa integer) OWNER TO fvelasquezl;

--
-- TOC entry 264 (class 1255 OID 57818)
-- Name: eliminar_productos(integer); Type: PROCEDURE; Schema: public; Owner: fvelasquezl
--

CREATE PROCEDURE public.eliminar_productos(IN iid_producto integer)
    LANGUAGE plpgsql
    AS $$ 
begin 
update productos set activo=false
where id_producto = iId_producto;
end;
$$;


ALTER PROCEDURE public.eliminar_productos(IN iid_producto integer) OWNER TO fvelasquezl;

--
-- TOC entry 261 (class 1255 OID 65836)
-- Name: eliminar_promocion(integer); Type: PROCEDURE; Schema: public; Owner: fvelasquezl
--

CREATE PROCEDURE public.eliminar_promocion(IN iid_promocion integer)
    LANGUAGE plpgsql
    AS $$
begin
    update promociones
    set activo = false
    where id_promocion = iId_promocion;
end;
$$;


ALTER PROCEDURE public.eliminar_promocion(IN iid_promocion integer) OWNER TO fvelasquezl;

--
-- TOC entry 299 (class 1255 OID 82203)
-- Name: func_calcular_ganancia_bruta(integer); Type: FUNCTION; Schema: public; Owner: fvelasquezl
--

CREATE FUNCTION public.func_calcular_ganancia_bruta(p_id_detalle integer) RETURNS numeric
    LANGUAGE plpgsql
    AS $$
DECLARE
    -- Declarar variables locales si es necesario, pero PostgreSQL puede inferir
    v_ganancia_bruta NUMERIC;
    v_descuento_aplicado NUMERIC;
BEGIN
    
    -- 1. Calcular la ganancia bruta (Ingreso por venta - Costo de compra del lote)
    SELECT
        (dv.cantidad * dv.precio_unitario) - (dv.cantidad * l.precio_compra),
        dv.descuento_aplicado
    INTO 
        v_ganancia_bruta,
        v_descuento_aplicado
    FROM detalle_ventas dv
    JOIN lotes l ON dv.id_lote = l.id_lote
    WHERE dv.id_detalle = p_id_detalle;
    
    -- 2. Restar el descuento aplicado para obtener la ganancia neta por item
    RETURN v_ganancia_bruta - COALESCE(v_descuento_aplicado, 0);

END;
$$;


ALTER FUNCTION public.func_calcular_ganancia_bruta(p_id_detalle integer) OWNER TO fvelasquezl;

--
-- TOC entry 301 (class 1255 OID 82206)
-- Name: func_desactivar_lote_agotado(); Type: FUNCTION; Schema: public; Owner: fvelasquezl
--

CREATE FUNCTION public.func_desactivar_lote_agotado() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    -- Verificar si la cantidad inicial es cero o menor
    IF NEW.cantidad_inicial <= 0 THEN
        NEW.activo = FALSE; -- Marcar el lote como inactivo para que no se pueda seguir vendiendo
    END IF;
    RETURN NEW;
END;
$$;


ALTER FUNCTION public.func_desactivar_lote_agotado() OWNER TO fvelasquezl;

--
-- TOC entry 300 (class 1255 OID 82204)
-- Name: func_reducir_stock_post_venta(); Type: FUNCTION; Schema: public; Owner: fvelasquezl
--

CREATE FUNCTION public.func_reducir_stock_post_venta() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    -- Restar la cantidad vendida (NEW.cantidad) del stock_actual del producto
    UPDATE productos
    SET stock_actual = stock_actual - NEW.cantidad
    WHERE id_producto = (SELECT id_producto FROM lotes WHERE id_lote = NEW.id_lote);
    RETURN NEW;
END;
$$;


ALTER FUNCTION public.func_reducir_stock_post_venta() OWNER TO fvelasquezl;

--
-- TOC entry 234 (class 1259 OID 57745)
-- Name: ventas; Type: TABLE; Schema: public; Owner: fvelasquezl
--

CREATE TABLE public.ventas (
    id_venta integer NOT NULL,
    id_cliente integer,
    id_usuario integer,
    subtotal numeric(10,2),
    descuento numeric(10,2) DEFAULT 0,
    total numeric(10,2),
    fecha_venta timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.ventas OWNER TO fvelasquezl;

--
-- TOC entry 295 (class 1255 OID 65843)
-- Name: obtener_ventas_por_cliente(integer); Type: FUNCTION; Schema: public; Owner: fvelasquezl
--

CREATE FUNCTION public.obtener_ventas_por_cliente(iid_cliente integer) RETURNS SETOF public.ventas
    LANGUAGE plpgsql
    AS $$
begin
    return query
    select *
    from ventas
    where id_cliente = iId_cliente
    order by fecha_venta desc;
end;
$$;


ALTER FUNCTION public.obtener_ventas_por_cliente(iid_cliente integer) OWNER TO fvelasquezl;

--
-- TOC entry 294 (class 1255 OID 65842)
-- Name: obtener_ventas_por_fecha(date, date); Type: FUNCTION; Schema: public; Owner: fvelasquezl
--

CREATE FUNCTION public.obtener_ventas_por_fecha(ifecha_inicio date, ifecha_fin date) RETURNS SETOF public.ventas
    LANGUAGE plpgsql
    AS $$
begin
    return query
    select *
    from ventas
    where fecha_venta between iFecha_inicio and iFecha_fin
    order by fecha_venta desc;
end;
$$;


ALTER FUNCTION public.obtener_ventas_por_fecha(ifecha_inicio date, ifecha_fin date) OWNER TO fvelasquezl;

--
-- TOC entry 297 (class 1255 OID 74157)
-- Name: productos_proximos_vencer(integer); Type: FUNCTION; Schema: public; Owner: fvelasquezl
--

CREATE FUNCTION public.productos_proximos_vencer(idias integer) RETURNS TABLE(id_lote integer, producto character varying, numero_lote character varying, fecha_vencimiento date, cantidad_inicial_lote integer)
    LANGUAGE plpgsql
    AS $$ 
BEGIN
    RETURN QUERY
    SELECT 
        l.id_lote,
        p.nombre::varchar,
        l.numero_lote::varchar,
        l.fecha_vencimiento,
        l.cantidad_inicial 
    FROM lotes l
    JOIN productos p ON l.id_producto = p.id_producto
    WHERE l.fecha_vencimiento BETWEEN CURRENT_DATE AND (CURRENT_DATE + iDias)
      AND l.activo = true;
END;
$$;


ALTER FUNCTION public.productos_proximos_vencer(idias integer) OWNER TO fvelasquezl;

--
-- TOC entry 296 (class 1255 OID 74156)
-- Name: productos_stock_bajo(integer); Type: FUNCTION; Schema: public; Owner: fvelasquezl
--

CREATE FUNCTION public.productos_stock_bajo(istockmin integer) RETURNS TABLE(id_producto integer, producto character varying, stock_actual integer, categoria character varying)
    LANGUAGE plpgsql
    AS $$ 
BEGIN
    RETURN QUERY
    SELECT 
        p.id_producto,
        p.nombre::varchar,
        p.stock_actual,
        c.nombre::varchar
    FROM productos p
    JOIN categorias c ON p.id_categoria = c.id_categoria
    WHERE p.stock_actual < iStockMin
      AND p.activo = true;
END;
$$;


ALTER FUNCTION public.productos_stock_bajo(istockmin integer) OWNER TO fvelasquezl;

--
-- TOC entry 298 (class 1255 OID 74158)
-- Name: ventas_por_fecha(date); Type: FUNCTION; Schema: public; Owner: fvelasquezl
--

CREATE FUNCTION public.ventas_por_fecha(ifecha date) RETURNS TABLE(id_venta integer, cliente character varying, total numeric, fecha_venta timestamp without time zone)
    LANGUAGE plpgsql
    AS $$
BEGIN
    RETURN QUERY
    SELECT 
        v.id_venta,
        CONCAT(c.nombres, ' ', c.apellidos)::varchar,
        v.total,
        v.fecha_venta
    FROM ventas v
    LEFT JOIN clientes c ON v.id_cliente = c.id_cliente
    WHERE v.fecha_venta::date = iFecha;
END;
$$;


ALTER FUNCTION public.ventas_por_fecha(ifecha date) OWNER TO fvelasquezl;

--
-- TOC entry 219 (class 1259 OID 57638)
-- Name: categorias_id_categoria_seq; Type: SEQUENCE; Schema: public; Owner: fvelasquezl
--

CREATE SEQUENCE public.categorias_id_categoria_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.categorias_id_categoria_seq OWNER TO fvelasquezl;

--
-- TOC entry 5128 (class 0 OID 0)
-- Dependencies: 219
-- Name: categorias_id_categoria_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: fvelasquezl
--

ALTER SEQUENCE public.categorias_id_categoria_seq OWNED BY public.categorias.id_categoria;


--
-- TOC entry 225 (class 1259 OID 57688)
-- Name: clientes_id_cliente_seq; Type: SEQUENCE; Schema: public; Owner: fvelasquezl
--

CREATE SEQUENCE public.clientes_id_cliente_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.clientes_id_cliente_seq OWNER TO fvelasquezl;

--
-- TOC entry 5129 (class 0 OID 0)
-- Dependencies: 225
-- Name: clientes_id_cliente_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: fvelasquezl
--

ALTER SEQUENCE public.clientes_id_cliente_seq OWNED BY public.clientes.id_cliente;


--
-- TOC entry 229 (class 1259 OID 57709)
-- Name: compras_id_compra_seq; Type: SEQUENCE; Schema: public; Owner: fvelasquezl
--

CREATE SEQUENCE public.compras_id_compra_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.compras_id_compra_seq OWNER TO fvelasquezl;

--
-- TOC entry 5130 (class 0 OID 0)
-- Dependencies: 229
-- Name: compras_id_compra_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: fvelasquezl
--

ALTER SEQUENCE public.compras_id_compra_seq OWNED BY public.compras.id_compra;


--
-- TOC entry 232 (class 1259 OID 57728)
-- Name: detalle_compras; Type: TABLE; Schema: public; Owner: fvelasquezl
--

CREATE TABLE public.detalle_compras (
    id_detalle_compra integer NOT NULL,
    id_compra integer,
    id_lote integer,
    cantidad integer
);


ALTER TABLE public.detalle_compras OWNER TO fvelasquezl;

--
-- TOC entry 231 (class 1259 OID 57727)
-- Name: detalle_compras_id_detalle_compra_seq; Type: SEQUENCE; Schema: public; Owner: fvelasquezl
--

CREATE SEQUENCE public.detalle_compras_id_detalle_compra_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.detalle_compras_id_detalle_compra_seq OWNER TO fvelasquezl;

--
-- TOC entry 5131 (class 0 OID 0)
-- Dependencies: 231
-- Name: detalle_compras_id_detalle_compra_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: fvelasquezl
--

ALTER SEQUENCE public.detalle_compras_id_detalle_compra_seq OWNED BY public.detalle_compras.id_detalle_compra;


--
-- TOC entry 236 (class 1259 OID 57764)
-- Name: detalle_ventas; Type: TABLE; Schema: public; Owner: fvelasquezl
--

CREATE TABLE public.detalle_ventas (
    id_detalle integer NOT NULL,
    id_venta integer,
    id_lote integer,
    cantidad integer NOT NULL,
    precio_unitario numeric(10,2),
    descuento_aplicado numeric(10,2) DEFAULT 0
);


ALTER TABLE public.detalle_ventas OWNER TO fvelasquezl;

--
-- TOC entry 235 (class 1259 OID 57763)
-- Name: detalle_ventas_id_detalle_seq; Type: SEQUENCE; Schema: public; Owner: fvelasquezl
--

CREATE SEQUENCE public.detalle_ventas_id_detalle_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.detalle_ventas_id_detalle_seq OWNER TO fvelasquezl;

--
-- TOC entry 5132 (class 0 OID 0)
-- Dependencies: 235
-- Name: detalle_ventas_id_detalle_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: fvelasquezl
--

ALTER SEQUENCE public.detalle_ventas_id_detalle_seq OWNED BY public.detalle_ventas.id_detalle;


--
-- TOC entry 243 (class 1259 OID 74084)
-- Name: directorio_clientes; Type: TABLE; Schema: public; Owner: fvelasquezl
--

CREATE TABLE public.directorio_clientes (
    dni character varying(8),
    nombre_completo text,
    telefono character varying(15),
    email character varying(255)
);


ALTER TABLE public.directorio_clientes OWNER TO fvelasquezl;

--
-- TOC entry 217 (class 1259 OID 57627)
-- Name: empresa_id_empresa_seq; Type: SEQUENCE; Schema: public; Owner: fvelasquezl
--

CREATE SEQUENCE public.empresa_id_empresa_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.empresa_id_empresa_seq OWNER TO fvelasquezl;

--
-- TOC entry 5133 (class 0 OID 0)
-- Dependencies: 217
-- Name: empresa_id_empresa_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: fvelasquezl
--

ALTER SEQUENCE public.empresa_id_empresa_seq OWNED BY public.empresa.id_empresa;


--
-- TOC entry 246 (class 1259 OID 74097)
-- Name: historial_compras_clientes; Type: TABLE; Schema: public; Owner: fvelasquezl
--

CREATE TABLE public.historial_compras_clientes (
    fecha_venta timestamp without time zone,
    id_venta integer,
    cliente text,
    monto_pagado numeric(10,2)
);


ALTER TABLE public.historial_compras_clientes OWNER TO fvelasquezl;

--
-- TOC entry 224 (class 1259 OID 57670)
-- Name: lotes; Type: TABLE; Schema: public; Owner: fvelasquezl
--

CREATE TABLE public.lotes (
    id_lote integer NOT NULL,
    id_producto integer NOT NULL,
    id_empresa_proveedor integer NOT NULL,
    numero_lote character varying(50),
    fecha_fabricacion date,
    fecha_vencimiento date,
    precio_compra numeric(10,2),
    cantidad_inicial integer,
    activo boolean DEFAULT true,
    fecha_ingreso date DEFAULT CURRENT_DATE
);


ALTER TABLE public.lotes OWNER TO fvelasquezl;

--
-- TOC entry 223 (class 1259 OID 57669)
-- Name: lotes_id_lote_seq; Type: SEQUENCE; Schema: public; Owner: fvelasquezl
--

CREATE SEQUENCE public.lotes_id_lote_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.lotes_id_lote_seq OWNER TO fvelasquezl;

--
-- TOC entry 5134 (class 0 OID 0)
-- Dependencies: 223
-- Name: lotes_id_lote_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: fvelasquezl
--

ALTER SEQUENCE public.lotes_id_lote_seq OWNED BY public.lotes.id_lote;


--
-- TOC entry 240 (class 1259 OID 57792)
-- Name: producto_promocion; Type: TABLE; Schema: public; Owner: fvelasquezl
--

CREATE TABLE public.producto_promocion (
    id_producto_promocion integer NOT NULL,
    id_producto integer,
    id_promocion integer
);


ALTER TABLE public.producto_promocion OWNER TO fvelasquezl;

--
-- TOC entry 239 (class 1259 OID 57791)
-- Name: producto_promocion_id_producto_promocion_seq; Type: SEQUENCE; Schema: public; Owner: fvelasquezl
--

CREATE SEQUENCE public.producto_promocion_id_producto_promocion_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.producto_promocion_id_producto_promocion_seq OWNER TO fvelasquezl;

--
-- TOC entry 5135 (class 0 OID 0)
-- Dependencies: 239
-- Name: producto_promocion_id_producto_promocion_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: fvelasquezl
--

ALTER SEQUENCE public.producto_promocion_id_producto_promocion_seq OWNED BY public.producto_promocion.id_producto_promocion;


--
-- TOC entry 221 (class 1259 OID 57647)
-- Name: productos_id_producto_seq; Type: SEQUENCE; Schema: public; Owner: fvelasquezl
--

CREATE SEQUENCE public.productos_id_producto_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.productos_id_producto_seq OWNER TO fvelasquezl;

--
-- TOC entry 5136 (class 0 OID 0)
-- Dependencies: 221
-- Name: productos_id_producto_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: fvelasquezl
--

ALTER SEQUENCE public.productos_id_producto_seq OWNED BY public.productos.id_producto;


--
-- TOC entry 242 (class 1259 OID 74079)
-- Name: productos_info; Type: TABLE; Schema: public; Owner: fvelasquezl
--

CREATE TABLE public.productos_info (
    id_producto integer,
    nombre character varying(255),
    descripcion character varying(255),
    precio_venta numeric(10,2),
    stock_actual integer
);


ALTER TABLE public.productos_info OWNER TO fvelasquezl;

--
-- TOC entry 241 (class 1259 OID 74056)
-- Name: productos_info_basica; Type: TABLE; Schema: public; Owner: fvelasquezl
--

CREATE TABLE public.productos_info_basica (
    id_producto integer,
    nombre character varying(255),
    descripcion character varying(255),
    precio_venta numeric(10,2)
);


ALTER TABLE public.productos_info_basica OWNER TO fvelasquezl;

--
-- TOC entry 254 (class 1259 OID 74137)
-- Name: productos_vista; Type: VIEW; Schema: public; Owner: fvelasquezl
--

CREATE VIEW public.productos_vista AS
 SELECT id_producto,
    nombre,
    descripcion,
    id_categoria
   FROM public.productos;


ALTER VIEW public.productos_vista OWNER TO fvelasquezl;

--
-- TOC entry 237 (class 1259 OID 57781)
-- Name: promociones_id_promocion_seq; Type: SEQUENCE; Schema: public; Owner: fvelasquezl
--

CREATE SEQUENCE public.promociones_id_promocion_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.promociones_id_promocion_seq OWNER TO fvelasquezl;

--
-- TOC entry 5137 (class 0 OID 0)
-- Dependencies: 237
-- Name: promociones_id_promocion_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: fvelasquezl
--

ALTER SEQUENCE public.promociones_id_promocion_seq OWNED BY public.promociones.id_promocion;


--
-- TOC entry 244 (class 1259 OID 74089)
-- Name: reporte_vencimientos; Type: TABLE; Schema: public; Owner: fvelasquezl
--

CREATE TABLE public.reporte_vencimientos (
    numero_lote character varying(50),
    nombre_producto character varying(255),
    fecha_vencimiento date,
    cantidad_lote integer
);


ALTER TABLE public.reporte_vencimientos OWNER TO fvelasquezl;

--
-- TOC entry 228 (class 1259 OID 57699)
-- Name: usuarios; Type: TABLE; Schema: public; Owner: fvelasquezl
--

CREATE TABLE public.usuarios (
    id_usuario integer NOT NULL,
    nombre_usuario character varying(100) NOT NULL,
    contrasenia character varying(255),
    nombres character varying(255) NOT NULL,
    apellidos character varying(255) NOT NULL,
    email character varying(255),
    rol character varying(50),
    fecha_creacion timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    activo boolean DEFAULT true
);


ALTER TABLE public.usuarios OWNER TO fvelasquezl;

--
-- TOC entry 227 (class 1259 OID 57698)
-- Name: usuarios_id_usuario_seq; Type: SEQUENCE; Schema: public; Owner: fvelasquezl
--

CREATE SEQUENCE public.usuarios_id_usuario_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.usuarios_id_usuario_seq OWNER TO fvelasquezl;

--
-- TOC entry 5138 (class 0 OID 0)
-- Dependencies: 227
-- Name: usuarios_id_usuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: fvelasquezl
--

ALTER SEQUENCE public.usuarios_id_usuario_seq OWNED BY public.usuarios.id_usuario;


--
-- TOC entry 255 (class 1259 OID 74141)
-- Name: v_inventario_bajo; Type: VIEW; Schema: public; Owner: fvelasquezl
--

CREATE VIEW public.v_inventario_bajo AS
 SELECT p.nombre AS producto,
    p.stock_actual AS stock,
    c.nombre AS categoria
   FROM (public.productos p
     JOIN public.categorias c ON ((p.id_categoria = c.id_categoria)))
  WHERE ((p.stock_actual < 20) AND (p.activo = true));


ALTER VIEW public.v_inventario_bajo OWNER TO fvelasquezl;

--
-- TOC entry 257 (class 1259 OID 74149)
-- Name: v_productos_vencidos; Type: VIEW; Schema: public; Owner: fvelasquezl
--

CREATE VIEW public.v_productos_vencidos AS
 SELECT p.nombre AS producto,
    l.numero_lote,
    l.fecha_vencimiento,
    l.cantidad_inicial AS cantidad_lote_origen
   FROM (public.lotes l
     JOIN public.productos p ON ((p.id_producto = l.id_producto)))
  WHERE ((l.fecha_vencimiento < CURRENT_DATE) AND (l.activo = true));


ALTER VIEW public.v_productos_vencidos OWNER TO fvelasquezl;

--
-- TOC entry 256 (class 1259 OID 74145)
-- Name: v_ventas_dia; Type: VIEW; Schema: public; Owner: fvelasquezl
--

CREATE VIEW public.v_ventas_dia AS
 SELECT v.id_venta AS id,
    v.fecha_venta AS fecha,
    concat(c.nombres, ' ', c.apellidos) AS cliente,
    v.total
   FROM (public.ventas v
     LEFT JOIN public.clientes c ON ((v.id_cliente = c.id_cliente)))
  WHERE ((v.fecha_venta)::date = CURRENT_DATE);


ALTER VIEW public.v_ventas_dia OWNER TO fvelasquezl;

--
-- TOC entry 245 (class 1259 OID 74092)
-- Name: valor_inventario; Type: TABLE; Schema: public; Owner: fvelasquezl
--

CREATE TABLE public.valor_inventario (
    id_producto integer,
    nombre character varying(255),
    categoria character varying(30),
    stock_actual integer,
    precio_venta numeric(10,2),
    valor_total_venta numeric
);


ALTER TABLE public.valor_inventario OWNER TO fvelasquezl;

--
-- TOC entry 233 (class 1259 OID 57744)
-- Name: ventas_id_venta_seq; Type: SEQUENCE; Schema: public; Owner: fvelasquezl
--

CREATE SEQUENCE public.ventas_id_venta_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.ventas_id_venta_seq OWNER TO fvelasquezl;

--
-- TOC entry 5139 (class 0 OID 0)
-- Dependencies: 233
-- Name: ventas_id_venta_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: fvelasquezl
--

ALTER SEQUENCE public.ventas_id_venta_seq OWNED BY public.ventas.id_venta;


--
-- TOC entry 249 (class 1259 OID 74112)
-- Name: vw_compras; Type: VIEW; Schema: public; Owner: fvelasquezl
--

CREATE VIEW public.vw_compras AS
 SELECT c.id_compra,
    e.nombre AS proveedor,
    u.nombre_usuario AS registrado_por,
    c.numero_factura,
    c.subtotal,
    c.impuesto,
    c.total,
    c.fecha_compra,
    c.fecha_registro
   FROM ((public.compras c
     JOIN public.empresa e ON ((c.id_proveedor = e.id_empresa)))
     JOIN public.usuarios u ON ((c.id_usuario = u.id_usuario)));


ALTER VIEW public.vw_compras OWNER TO fvelasquezl;

--
-- TOC entry 250 (class 1259 OID 74117)
-- Name: vw_detalle_compras; Type: VIEW; Schema: public; Owner: fvelasquezl
--

CREATE VIEW public.vw_detalle_compras AS
 SELECT dc.id_detalle_compra,
    c.id_compra,
    l.id_lote,
    p.nombre AS producto,
    dc.cantidad,
    l.precio_compra,
    ((dc.cantidad)::numeric * l.precio_compra) AS total_linea
   FROM (((public.detalle_compras dc
     JOIN public.compras c ON ((dc.id_compra = c.id_compra)))
     JOIN public.lotes l ON ((dc.id_lote = l.id_lote)))
     JOIN public.productos p ON ((l.id_producto = p.id_producto)));


ALTER VIEW public.vw_detalle_compras OWNER TO fvelasquezl;

--
-- TOC entry 252 (class 1259 OID 74127)
-- Name: vw_detalle_ventas; Type: VIEW; Schema: public; Owner: fvelasquezl
--

CREATE VIEW public.vw_detalle_ventas AS
 SELECT dv.id_detalle,
    v.id_venta,
    p.nombre AS producto,
    dv.cantidad,
    dv.precio_unitario,
    dv.descuento_aplicado,
    (((dv.cantidad)::numeric * dv.precio_unitario) - dv.descuento_aplicado) AS total_linea
   FROM (((public.detalle_ventas dv
     JOIN public.ventas v ON ((dv.id_venta = v.id_venta)))
     JOIN public.lotes l ON ((dv.id_lote = l.id_lote)))
     JOIN public.productos p ON ((l.id_producto = p.id_producto)));


ALTER VIEW public.vw_detalle_ventas OWNER TO fvelasquezl;

--
-- TOC entry 248 (class 1259 OID 74107)
-- Name: vw_lotes_detallados; Type: VIEW; Schema: public; Owner: fvelasquezl
--

CREATE VIEW public.vw_lotes_detallados AS
 SELECT l.id_lote,
    p.nombre AS producto,
    e.nombre AS proveedor,
    l.numero_lote,
    l.fecha_fabricacion,
    l.fecha_vencimiento,
    l.precio_compra,
    l.cantidad_inicial
   FROM ((public.lotes l
     JOIN public.productos p ON ((l.id_producto = p.id_producto)))
     JOIN public.empresa e ON ((l.id_empresa_proveedor = e.id_empresa)));


ALTER VIEW public.vw_lotes_detallados OWNER TO fvelasquezl;

--
-- TOC entry 247 (class 1259 OID 74102)
-- Name: vw_productos_detallados; Type: VIEW; Schema: public; Owner: fvelasquezl
--

CREATE VIEW public.vw_productos_detallados AS
 SELECT p.id_producto,
    p.nombre AS producto,
    p.descripcion,
    c.nombre AS categoria,
    e.nombre AS fabricante,
    p.precio_venta,
    p.stock_actual,
    p.stock_minimo,
    p.activo
   FROM ((public.productos p
     LEFT JOIN public.categorias c ON ((p.id_categoria = c.id_categoria)))
     LEFT JOIN public.empresa e ON ((p.id_empresa_fabricante = e.id_empresa)));


ALTER VIEW public.vw_productos_detallados OWNER TO fvelasquezl;

--
-- TOC entry 253 (class 1259 OID 74132)
-- Name: vw_promociones_productos; Type: VIEW; Schema: public; Owner: fvelasquezl
--

CREATE VIEW public.vw_promociones_productos AS
 SELECT pr.id_promocion,
    pr.nombre AS promocion,
    pr.tipo_descuento,
    pr.valor_descuento,
    p.id_producto,
    p.nombre AS producto,
    pr.fecha_inicio,
    pr.fecha_fin
   FROM ((public.producto_promocion pp
     JOIN public.promociones pr ON ((pp.id_promocion = pr.id_promocion)))
     JOIN public.productos p ON ((pp.id_producto = p.id_producto)))
  WHERE (pr.activo = true);


ALTER VIEW public.vw_promociones_productos OWNER TO fvelasquezl;

--
-- TOC entry 251 (class 1259 OID 74122)
-- Name: vw_ventas; Type: VIEW; Schema: public; Owner: fvelasquezl
--

CREATE VIEW public.vw_ventas AS
 SELECT v.id_venta,
    concat(c.nombres, ' ', c.apellidos) AS cliente,
    u.nombre_usuario AS vendedor,
    v.subtotal,
    v.descuento,
    v.total,
    v.fecha_venta
   FROM ((public.ventas v
     LEFT JOIN public.clientes c ON ((v.id_cliente = c.id_cliente)))
     LEFT JOIN public.usuarios u ON ((v.id_usuario = u.id_usuario)));


ALTER VIEW public.vw_ventas OWNER TO fvelasquezl;

--
-- TOC entry 4901 (class 2604 OID 57642)
-- Name: categorias id_categoria; Type: DEFAULT; Schema: public; Owner: fvelasquezl
--

ALTER TABLE ONLY public.categorias ALTER COLUMN id_categoria SET DEFAULT nextval('public.categorias_id_categoria_seq'::regclass);


--
-- TOC entry 4911 (class 2604 OID 57692)
-- Name: clientes id_cliente; Type: DEFAULT; Schema: public; Owner: fvelasquezl
--

ALTER TABLE ONLY public.clientes ALTER COLUMN id_cliente SET DEFAULT nextval('public.clientes_id_cliente_seq'::regclass);


--
-- TOC entry 4916 (class 2604 OID 57713)
-- Name: compras id_compra; Type: DEFAULT; Schema: public; Owner: fvelasquezl
--

ALTER TABLE ONLY public.compras ALTER COLUMN id_compra SET DEFAULT nextval('public.compras_id_compra_seq'::regclass);


--
-- TOC entry 4918 (class 2604 OID 57731)
-- Name: detalle_compras id_detalle_compra; Type: DEFAULT; Schema: public; Owner: fvelasquezl
--

ALTER TABLE ONLY public.detalle_compras ALTER COLUMN id_detalle_compra SET DEFAULT nextval('public.detalle_compras_id_detalle_compra_seq'::regclass);


--
-- TOC entry 4922 (class 2604 OID 57767)
-- Name: detalle_ventas id_detalle; Type: DEFAULT; Schema: public; Owner: fvelasquezl
--

ALTER TABLE ONLY public.detalle_ventas ALTER COLUMN id_detalle SET DEFAULT nextval('public.detalle_ventas_id_detalle_seq'::regclass);


--
-- TOC entry 4898 (class 2604 OID 57631)
-- Name: empresa id_empresa; Type: DEFAULT; Schema: public; Owner: fvelasquezl
--

ALTER TABLE ONLY public.empresa ALTER COLUMN id_empresa SET DEFAULT nextval('public.empresa_id_empresa_seq'::regclass);


--
-- TOC entry 4908 (class 2604 OID 57673)
-- Name: lotes id_lote; Type: DEFAULT; Schema: public; Owner: fvelasquezl
--

ALTER TABLE ONLY public.lotes ALTER COLUMN id_lote SET DEFAULT nextval('public.lotes_id_lote_seq'::regclass);


--
-- TOC entry 4926 (class 2604 OID 57795)
-- Name: producto_promocion id_producto_promocion; Type: DEFAULT; Schema: public; Owner: fvelasquezl
--

ALTER TABLE ONLY public.producto_promocion ALTER COLUMN id_producto_promocion SET DEFAULT nextval('public.producto_promocion_id_producto_promocion_seq'::regclass);


--
-- TOC entry 4904 (class 2604 OID 57651)
-- Name: productos id_producto; Type: DEFAULT; Schema: public; Owner: fvelasquezl
--

ALTER TABLE ONLY public.productos ALTER COLUMN id_producto SET DEFAULT nextval('public.productos_id_producto_seq'::regclass);


--
-- TOC entry 4924 (class 2604 OID 57785)
-- Name: promociones id_promocion; Type: DEFAULT; Schema: public; Owner: fvelasquezl
--

ALTER TABLE ONLY public.promociones ALTER COLUMN id_promocion SET DEFAULT nextval('public.promociones_id_promocion_seq'::regclass);


--
-- TOC entry 4913 (class 2604 OID 57702)
-- Name: usuarios id_usuario; Type: DEFAULT; Schema: public; Owner: fvelasquezl
--

ALTER TABLE ONLY public.usuarios ALTER COLUMN id_usuario SET DEFAULT nextval('public.usuarios_id_usuario_seq'::regclass);


--
-- TOC entry 4919 (class 2604 OID 57748)
-- Name: ventas id_venta; Type: DEFAULT; Schema: public; Owner: fvelasquezl
--

ALTER TABLE ONLY public.ventas ALTER COLUMN id_venta SET DEFAULT nextval('public.ventas_id_venta_seq'::regclass);


--
-- TOC entry 4930 (class 2606 OID 57646)
-- Name: categorias categorias_pkey; Type: CONSTRAINT; Schema: public; Owner: fvelasquezl
--

ALTER TABLE ONLY public.categorias
    ADD CONSTRAINT categorias_pkey PRIMARY KEY (id_categoria);


--
-- TOC entry 4936 (class 2606 OID 57697)
-- Name: clientes clientes_pkey; Type: CONSTRAINT; Schema: public; Owner: fvelasquezl
--

ALTER TABLE ONLY public.clientes
    ADD CONSTRAINT clientes_pkey PRIMARY KEY (id_cliente);


--
-- TOC entry 4940 (class 2606 OID 57716)
-- Name: compras compras_pkey; Type: CONSTRAINT; Schema: public; Owner: fvelasquezl
--

ALTER TABLE ONLY public.compras
    ADD CONSTRAINT compras_pkey PRIMARY KEY (id_compra);


--
-- TOC entry 4942 (class 2606 OID 57733)
-- Name: detalle_compras detalle_compras_pkey; Type: CONSTRAINT; Schema: public; Owner: fvelasquezl
--

ALTER TABLE ONLY public.detalle_compras
    ADD CONSTRAINT detalle_compras_pkey PRIMARY KEY (id_detalle_compra);


--
-- TOC entry 4946 (class 2606 OID 57770)
-- Name: detalle_ventas detalle_ventas_pkey; Type: CONSTRAINT; Schema: public; Owner: fvelasquezl
--

ALTER TABLE ONLY public.detalle_ventas
    ADD CONSTRAINT detalle_ventas_pkey PRIMARY KEY (id_detalle);


--
-- TOC entry 4928 (class 2606 OID 57637)
-- Name: empresa empresa_pkey; Type: CONSTRAINT; Schema: public; Owner: fvelasquezl
--

ALTER TABLE ONLY public.empresa
    ADD CONSTRAINT empresa_pkey PRIMARY KEY (id_empresa);


--
-- TOC entry 4934 (class 2606 OID 57677)
-- Name: lotes lotes_pkey; Type: CONSTRAINT; Schema: public; Owner: fvelasquezl
--

ALTER TABLE ONLY public.lotes
    ADD CONSTRAINT lotes_pkey PRIMARY KEY (id_lote);


--
-- TOC entry 4950 (class 2606 OID 57797)
-- Name: producto_promocion producto_promocion_pkey; Type: CONSTRAINT; Schema: public; Owner: fvelasquezl
--

ALTER TABLE ONLY public.producto_promocion
    ADD CONSTRAINT producto_promocion_pkey PRIMARY KEY (id_producto_promocion);


--
-- TOC entry 4932 (class 2606 OID 57658)
-- Name: productos productos_pkey; Type: CONSTRAINT; Schema: public; Owner: fvelasquezl
--

ALTER TABLE ONLY public.productos
    ADD CONSTRAINT productos_pkey PRIMARY KEY (id_producto);


--
-- TOC entry 4948 (class 2606 OID 57790)
-- Name: promociones promociones_pkey; Type: CONSTRAINT; Schema: public; Owner: fvelasquezl
--

ALTER TABLE ONLY public.promociones
    ADD CONSTRAINT promociones_pkey PRIMARY KEY (id_promocion);


--
-- TOC entry 4938 (class 2606 OID 57708)
-- Name: usuarios usuarios_pkey; Type: CONSTRAINT; Schema: public; Owner: fvelasquezl
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT usuarios_pkey PRIMARY KEY (id_usuario);


--
-- TOC entry 4944 (class 2606 OID 57752)
-- Name: ventas ventas_pkey; Type: CONSTRAINT; Schema: public; Owner: fvelasquezl
--

ALTER TABLE ONLY public.ventas
    ADD CONSTRAINT ventas_pkey PRIMARY KEY (id_venta);


--
-- TOC entry 4965 (class 2620 OID 82207)
-- Name: lotes trg_desactivar_cero; Type: TRIGGER; Schema: public; Owner: fvelasquezl
--

CREATE TRIGGER trg_desactivar_cero BEFORE UPDATE ON public.lotes FOR EACH ROW EXECUTE FUNCTION public.func_desactivar_lote_agotado();


--
-- TOC entry 4966 (class 2620 OID 82205)
-- Name: detalle_ventas trg_reducir_stock; Type: TRIGGER; Schema: public; Owner: fvelasquezl
--

CREATE TRIGGER trg_reducir_stock AFTER INSERT ON public.detalle_ventas FOR EACH ROW EXECUTE FUNCTION public.func_reducir_stock_post_venta();


--
-- TOC entry 4955 (class 2606 OID 57717)
-- Name: compras compras_id_proveedor_fkey; Type: FK CONSTRAINT; Schema: public; Owner: fvelasquezl
--

ALTER TABLE ONLY public.compras
    ADD CONSTRAINT compras_id_proveedor_fkey FOREIGN KEY (id_proveedor) REFERENCES public.empresa(id_empresa);


--
-- TOC entry 4956 (class 2606 OID 57722)
-- Name: compras compras_id_usuario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: fvelasquezl
--

ALTER TABLE ONLY public.compras
    ADD CONSTRAINT compras_id_usuario_fkey FOREIGN KEY (id_usuario) REFERENCES public.usuarios(id_usuario);


--
-- TOC entry 4957 (class 2606 OID 57734)
-- Name: detalle_compras detalle_compras_id_compra_fkey; Type: FK CONSTRAINT; Schema: public; Owner: fvelasquezl
--

ALTER TABLE ONLY public.detalle_compras
    ADD CONSTRAINT detalle_compras_id_compra_fkey FOREIGN KEY (id_compra) REFERENCES public.compras(id_compra) ON DELETE CASCADE;


--
-- TOC entry 4958 (class 2606 OID 57739)
-- Name: detalle_compras detalle_compras_id_lote_fkey; Type: FK CONSTRAINT; Schema: public; Owner: fvelasquezl
--

ALTER TABLE ONLY public.detalle_compras
    ADD CONSTRAINT detalle_compras_id_lote_fkey FOREIGN KEY (id_lote) REFERENCES public.lotes(id_lote);


--
-- TOC entry 4961 (class 2606 OID 57776)
-- Name: detalle_ventas detalle_ventas_id_lote_fkey; Type: FK CONSTRAINT; Schema: public; Owner: fvelasquezl
--

ALTER TABLE ONLY public.detalle_ventas
    ADD CONSTRAINT detalle_ventas_id_lote_fkey FOREIGN KEY (id_lote) REFERENCES public.lotes(id_lote);


--
-- TOC entry 4962 (class 2606 OID 57771)
-- Name: detalle_ventas detalle_ventas_id_venta_fkey; Type: FK CONSTRAINT; Schema: public; Owner: fvelasquezl
--

ALTER TABLE ONLY public.detalle_ventas
    ADD CONSTRAINT detalle_ventas_id_venta_fkey FOREIGN KEY (id_venta) REFERENCES public.ventas(id_venta) ON DELETE CASCADE;


--
-- TOC entry 4953 (class 2606 OID 57683)
-- Name: lotes lotes_id_empresa_proveedor_fkey; Type: FK CONSTRAINT; Schema: public; Owner: fvelasquezl
--

ALTER TABLE ONLY public.lotes
    ADD CONSTRAINT lotes_id_empresa_proveedor_fkey FOREIGN KEY (id_empresa_proveedor) REFERENCES public.empresa(id_empresa);


--
-- TOC entry 4954 (class 2606 OID 57678)
-- Name: lotes lotes_id_producto_fkey; Type: FK CONSTRAINT; Schema: public; Owner: fvelasquezl
--

ALTER TABLE ONLY public.lotes
    ADD CONSTRAINT lotes_id_producto_fkey FOREIGN KEY (id_producto) REFERENCES public.productos(id_producto);


--
-- TOC entry 4963 (class 2606 OID 57798)
-- Name: producto_promocion producto_promocion_id_producto_fkey; Type: FK CONSTRAINT; Schema: public; Owner: fvelasquezl
--

ALTER TABLE ONLY public.producto_promocion
    ADD CONSTRAINT producto_promocion_id_producto_fkey FOREIGN KEY (id_producto) REFERENCES public.productos(id_producto);


--
-- TOC entry 4964 (class 2606 OID 57803)
-- Name: producto_promocion producto_promocion_id_promocion_fkey; Type: FK CONSTRAINT; Schema: public; Owner: fvelasquezl
--

ALTER TABLE ONLY public.producto_promocion
    ADD CONSTRAINT producto_promocion_id_promocion_fkey FOREIGN KEY (id_promocion) REFERENCES public.promociones(id_promocion);


--
-- TOC entry 4951 (class 2606 OID 57659)
-- Name: productos productos_id_categoria_fkey; Type: FK CONSTRAINT; Schema: public; Owner: fvelasquezl
--

ALTER TABLE ONLY public.productos
    ADD CONSTRAINT productos_id_categoria_fkey FOREIGN KEY (id_categoria) REFERENCES public.categorias(id_categoria);


--
-- TOC entry 4952 (class 2606 OID 57664)
-- Name: productos productos_id_empresa_fabricante_fkey; Type: FK CONSTRAINT; Schema: public; Owner: fvelasquezl
--

ALTER TABLE ONLY public.productos
    ADD CONSTRAINT productos_id_empresa_fabricante_fkey FOREIGN KEY (id_empresa_fabricante) REFERENCES public.empresa(id_empresa);


--
-- TOC entry 4959 (class 2606 OID 57753)
-- Name: ventas ventas_id_cliente_fkey; Type: FK CONSTRAINT; Schema: public; Owner: fvelasquezl
--

ALTER TABLE ONLY public.ventas
    ADD CONSTRAINT ventas_id_cliente_fkey FOREIGN KEY (id_cliente) REFERENCES public.clientes(id_cliente);


--
-- TOC entry 4960 (class 2606 OID 57758)
-- Name: ventas ventas_id_usuario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: fvelasquezl
--

ALTER TABLE ONLY public.ventas
    ADD CONSTRAINT ventas_id_usuario_fkey FOREIGN KEY (id_usuario) REFERENCES public.usuarios(id_usuario);


-- Completed on 2026-05-15 23:24:40

--
-- PostgreSQL database dump complete
--

\unrestrict N0A4FYxDVvMkjla5XT7CwkqdWNDNEtmS7Y4qAqatQ6wIlwd9itxYCLqDJbRE2VA

