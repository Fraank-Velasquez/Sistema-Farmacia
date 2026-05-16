--
-- PostgreSQL database dump
--

\restrict lIsyJLY0kMn8u2kisb6PIfdRig6I6CTaSgS93U2oUwZXedOhEimYkWXx9ILY4qW

-- Dumped from database version 17.6
-- Dumped by pg_dump version 17.6

-- Started on 2026-05-15 23:17:58

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
-- TOC entry 5097 (class 0 OID 57639)
-- Dependencies: 220
-- Data for Name: categorias; Type: TABLE DATA; Schema: public; Owner: fvelasquezl
--

COPY public.categorias (id_categoria, nombre, descripcion, fecha_creacion, activo) FROM stdin;
1	Analgesicos	Medicamentos para dolor	2023-01-10 08:00:00	t
2	Antibioticos	Antibióticos de uso común	2023-01-12 08:00:00	t
3	Antiinflamatorios	AINEs orales y tópicos	2023-01-15 08:00:00	t
4	Antisepticos	Soluciones yodadas y alcoholes	2023-02-01 08:00:00	t
5	Inyectables	Medicamentos para administración parenteral	2023-02-10 08:00:00	t
6	Insulina	Productos para diabetes	2023-02-20 08:00:00	t
7	Dispositivos	Equipo médico reutilizable	2023-03-01 08:00:00	t
8	Consumibles	Jeringas, agujas, guantes, gasas	2023-03-10 08:00:00	t
9	Equipos Pequenos	Termómetros, estetoscopios	2023-03-20 08:00:00	t
10	Líquidos Intravenosos	Soluciones salinas y dextrosa	2023-04-01 08:00:00	t
11	Suturas	Material de sutura	2023-04-05 08:00:00	t
12	Antiemeticos	Medicamentos para náuseas	2023-04-10 08:00:00	t
13	Antihipertensivos	Medicamentos para presión arterial	2023-04-20 08:00:00	t
14	Antidiabeticos	Orales y complementarios	2023-05-01 08:00:00	t
15	Gases Medicinales	Oxígeno y componentes	2023-05-10 08:00:00	t
16	Anestesia	Agentes anestésicos	2023-05-20 08:00:00	t
17	Antifungicos	Medicamentos para hongos	2023-06-01 08:00:00	t
18	Vitaminas	Suplementos vitamínicos	2023-06-10 08:00:00	t
19	Digestivos	Antiácidos y protectores	2023-06-20 08:00:00	t
20	Respiratorio	Broncodilatadores e inhaladores	2023-07-01 08:00:00	t
21	Vacunas	Vacunas y sueros	2023-07-10 08:00:00	t
22	Laboratorio	Reactivos y kits de laboratorio	2023-07-20 08:00:00	t
23	Cardiologia	Medicamentos cardiológicos	2023-07-30 08:00:00	t
24	Dermatologia	Cremas y tratamientos de piel	2023-08-05 08:00:00	t
25	Urología	Productos para urología	2023-08-15 08:00:00	t
\.


--
-- TOC entry 5103 (class 0 OID 57689)
-- Dependencies: 226
-- Data for Name: clientes; Type: TABLE DATA; Schema: public; Owner: fvelasquezl
--

COPY public.clientes (id_cliente, dni, nombres, apellidos, telefono, email, activo) FROM stdin;
2	23456789	Luis	García	944223456	luis.garcia@mail.com	t
3	34567890	María	Rodríguez	944323456	maria.rodriguez@mail.com	t
4	45678901	Carlos	Santos	944423456	carlos.santos@mail.com	t
5	56789012	Sofía	Mendoza	944523456	sofia.mendoza@mail.com	t
6	67890123	Pedro	Alvarez	944623456	pedro.alvarez@mail.com	t
7	78901234	Lucía	Torres	944723456	lucia.torres@mail.com	t
8	89012345	Diego	Ramos	944823456	diego.ramos@mail.com	t
9	90123456	Paola	Vargas	944923456	paola.vargas@mail.com	t
10	01234567	Javier	Cruz	945023456	javier.cruz@mail.com	t
11	11223344	Elena	Suarez	945123456	elena.suarez@mail.com	t
12	22334455	Miguel	Flores	945223456	miguel.flores@mail.com	t
13	33445566	Rosa	Navarro	945323456	rosa.navarro@mail.com	t
14	44556677	Andrés	Ruiz	945423456	andres.ruiz@mail.com	t
15	55667788	Carla	Vega	945523456	carla.vega@mail.com	t
16	66778899	Bruno	Castillo	945623456	bruno.castillo@mail.com	t
17	77889900	Marta	Quispe	945723456	marta.quispe@mail.com	t
18	88990011	Hugo	Lopez	945823456	hugo.lopez@mail.com	t
19	99001122	Julia	Ramos	945923456	julia.ramos@mail.com	t
20	10101010	Ronaldo	Gómez	946023456	ronaldo.gomez@mail.com	t
21	12121212	Nadia	Benítez	946123456	nadia.benitez@mail.com	t
22	13131313	Óscar	Paz	946223456	oscar.paz@mail.com	t
23	14141414	Isabel	Chávez	946323456	isabel.chavez@mail.com	t
24	15151515	Tony	Velásquez	946423456	tony.velasquez@mail.com	t
25	16161616	Verónica	Huamán	946523456	veronica.huaman@mail.com	t
1	12345678	Cliente	Generico	999999999	generico@mail.com	t
\.


--
-- TOC entry 5095 (class 0 OID 57628)
-- Dependencies: 218
-- Data for Name: empresa; Type: TABLE DATA; Schema: public; Owner: fvelasquezl
--

COPY public.empresa (id_empresa, nombre, telefono, email, direccion, ciudad, ruc, tipo_empresa, fecha_registro, activo) FROM stdin;
1	Laboratorios BioMed S.A.	074-123456	contacto@biomed.pe	Av. Principal 120	Piura	20123456789	laboratorio	2024-02-01 09:10:00	t
3	Insumos Médicos Norte SAC	074-334455	info@insumosnorte.pe	Jr. Salud 88	Piura	20678912345	proveedor	2024-05-20 11:20:00	t
4	Laboratorios Andina S.A.C.	01-555444	andina@lab.com.pe	Calle 7 #200	Lima	20456789123	laboratorio	2023-12-10 08:00:00	t
5	Mediconta Distribuciones	074-445566	contacto@mediconta.pe	Av. Universitaria 300	Piura	20789123456	proveedor	2024-01-05 14:00:00	t
6	Farmacéutica Río Bello	01-777666	ventas@riobello.com	Zona Industrial 12	Lima	20891234567	laboratorio	2024-06-02 09:30:00	t
7	Equipos Clínicos del Norte	074-556677	equipos@clinico.pe	Parque Industrial 8	Piura	20901234567	proveedor	2024-04-10 12:45:00	t
8	Proveedor MedicalTools	01-888777	info@medicaltools.pe	Av. Salud 55	Trujillo	21012345678	proveedor	2024-07-01 10:10:00	t
9	Laboratorios Sigma Perú	01-999888	sigma@lab.pe	Calle Ciencia 10	Lima	21123456789	laboratorio	2024-02-25 09:50:00	t
10	Distribuciones Hospitalarias S.A.	074-667788	contacto@dist-hosp.pe	Av. Hospital 100	Piura	21234567890	proveedor	2024-08-01 08:30:00	t
12	Laboratorios VidaNatural	01-121212	info@vidanatural.pe	Av. Salud 400	Lima	21456789012	laboratorio	2024-03-03 15:45:00	t
13	Suministros Químicos SRL	01-131313	ventas@suministrosq.pe	Zona Química 3	Lima	21567890123	proveedor	2024-05-05 09:15:00	t
14	Ambulancias y Servicios Médicos	074-889900	contacto@ambulancias.pe	Calle Emergencia 7	Piura	21678901234	ambos	2024-02-18 13:20:00	t
15	Laboratorio Central S.A.	01-141414	central@lab.pe	Av. Central 77	Lima	21789012345	laboratorio	2024-06-18 10:40:00	t
16	Importadora Meditech	01-151515	info@meditech.pe	Parque Tec 5	Lima	21890123456	proveedor	2024-07-20 09:00:00	t
17	Proveedor Clínico Norte	074-990011	ventas@clinicnorte.pe	Jr. Salud 44	Piura	21901234567	proveedor	2024-01-30 16:00:00	t
18	Hospitalaria Premium	01-161616	contacto@hospremium.pe	Av. Esperanza 9	Lima	22012345678	proveedor	2024-08-12 12:00:00	t
19	Laboratorios Omega	01-171717	omega@lab.com	Calle Investigación 3	Lima	22123456789	laboratorio	2024-04-04 14:30:00	t
20	Surtimed Perú S.A.	074-223300	info@surtimed.pe	Av. Comercio 12	Piura	22234567890	proveedor	2024-05-11 10:05:00	t
21	Biofarma Internacional	01-181818	ventas@biofarma.pe	Zona Industrial 2	Lima	22345678901	laboratorio	2024-02-28 09:25:00	t
22	Materiales Médicos Express	074-334400	express@medmat.pe	Calle 9 #9	Piura	22456789012	proveedor	2024-03-22 11:35:00	t
23	Laboratorios NuevaVida	01-191919	contacto@nuevavida.pe	Av. Salud 200	Lima	22567890123	laboratorio	2024-06-30 08:50:00	t
24	Distribuciones FarmaCenter	01-202020	ventas@farmacenter.pe	Av. Central 5	Lima	22678901234	proveedor	2024-07-25 09:10:00	t
25	Equipos y Suministros Médicos SAC	074-445577	info@equisum.pe	Jr. Industria 33	Piura	22789012345	proveedor	2024-08-05 10:55:00	t
11	BioEquipos SAC	074-778899	ventas@bioequipos.pe	Jr. Progreso 22	Piura	21345678901	proveedor	2024-09-10 11:00:00	t
2	Distribuidora SaludPlus	074-223344	ventas@saludplus.pe	Calle Comercio 45	Piura	20567891234	proveedor	2024-03-15 10:00:00	t
\.


--
-- TOC entry 5105 (class 0 OID 57699)
-- Dependencies: 228
-- Data for Name: usuarios; Type: TABLE DATA; Schema: public; Owner: fvelasquezl
--

COPY public.usuarios (id_usuario, nombre_usuario, contrasenia, nombres, apellidos, email, rol, fecha_creacion, activo) FROM stdin;
1	admin	$2b$10$hashadmin	Admin	Sistema	admin@clinica.pe	admin	2024-01-01 08:00:00	t
2	almacen1	$2b$10$hashalm1	Juan	Lopez	juan.lopez@clinica.pe	almacen	2024-01-05 09:00:00	t
3	almacen2	$2b$10$hashalm2	Rosa	Gonzales	rosa.gonzales@clinica.pe	almacen	2024-01-08 09:30:00	t
4	ventas1	$2b$10$hashven1	Laura	Martinez	laura.martinez@clinica.pe	vendedor	2024-02-01 10:00:00	t
5	ventas2	$2b$10$hashven2	Pedro	Salas	pedro.salas@clinica.pe	vendedor	2024-02-03 10:10:00	t
6	conta1	$2b$10$hashcon1	Mónica	Reyes	monica.reyes@clinica.pe	contabilidad	2024-02-10 11:00:00	t
7	lab1	$2b$10$hashlab1	Diego	Paredes	diego.paredes@clinica.pe	laboratorio	2024-03-01 08:30:00	t
8	recepcion	$2b$10$hashrec	Ana	Guerra	recepcion@clinica.pe	recepcion	2024-03-05 09:00:00	t
9	enf1	$2b$10$hashenf1	Cecilia	Rojas	cecilia.rojas@clinica.pe	enfermeria	2024-03-10 09:20:00	t
10	med1	$2b$10$hashmed1	Dr. Carlos	Molina	carlos.molina@clinica.pe	medico	2024-03-15 10:00:00	t
11	med2	$2b$10$hashmed2	Dra. Marta	Suárez	marta.suarez@clinica.pe	medico	2024-03-18 10:30:00	t
12	adm1	$2b$10$hashadm1	Raúl	Gutiérrez	raul.gutierrez@clinica.pe	administracion	2024-04-01 11:00:00	t
13	log1	$2b$10$hashlog1	Patricia	Luna	patricia.luna@clinica.pe	logistica	2024-04-05 11:10:00	t
14	farm1	$2b$10$hashfarm1	Roberto	Chávez	roberto.chavez@clinica.pe	farmacia	2024-04-10 12:00:00	t
15	aux1	$2b$10$hashaux1	Silvia	Herrera	silvia.herrera@clinica.pe	auxiliar	2024-04-20 08:40:00	t
16	tec1	$2b$10$hashtec1	Fernando	Ortega	fernando.ortega@clinica.pe	tecnico	2024-05-01 09:50:00	t
17	compras1	$2b$10$hashcomp1	Esteban	Pinto	esteban.pinto@clinica.pe	compras	2024-05-10 10:10:00	t
18	ventas3	$2b$10$hashven3	Lorena	Salazar	lorena.salazar@clinica.pe	vendedor	2024-05-20 11:20:00	t
19	almacen3	$2b$10$hashalm3	Miguel	Soto	miguel.soto@clinica.pe	almacen	2024-06-01 08:30:00	t
20	calidad	$2b$10$hashcal	Carla	Morales	carla.morales@clinica.pe	calidad	2024-06-05 09:15:00	t
21	auditor	$2b$10$hashaud	Rene	Vega	rene.vega@clinica.pe	auditor	2024-06-10 10:50:00	t
22	soporte	$2b$10$hashsup	Marco	Paz	soporte@clinica.pe	soporte	2024-06-15 11:30:00	t
23	inv1	$2b$10$hashinv1	Verónica	Cano	veronica.cano@clinica.pe	inventario	2024-06-20 08:00:00	t
24	reserva	$2b$10$hashres	Hernán	Saldaña	hernan.saldana@clinica.pe	reserva	2024-06-25 09:00:00	t
25	admin2	$2b$10$hashadmin2	Luciano	Ferrer	luciano.ferrer@clinica.pe	admin	2024-07-01 10:00:00	t
27	usuario	usuario123	Usuario	NA	NA	Usuario	2025-12-05 20:44:55.073079	t
26	fvelasquezl	admin123	Frank	Velasquez	fvelasquezl@gmail.com	Administrador	2025-12-04 11:51:37.463501	t
\.


--
-- TOC entry 5107 (class 0 OID 57710)
-- Dependencies: 230
-- Data for Name: compras; Type: TABLE DATA; Schema: public; Owner: fvelasquezl
--

COPY public.compras (id_compra, id_proveedor, id_usuario, numero_factura, subtotal, impuesto, total, fecha_compra, observaciones, fecha_registro) FROM stdin;
1	2	17	F2024-001	350.00	63.00	413.00	2024-01-10	\N	2024-01-10 09:30:00
2	9	19	F2024-012	480.00	86.40	566.40	2024-03-06	\N	2024-03-06 10:40:00
3	4	17	F2024-020	600.00	108.00	708.00	2024-01-30	\N	2024-01-30 11:20:00
4	15	17	F2024-030	880.00	158.40	1038.40	2024-01-16	\N	2024-01-16 12:00:00
5	21	17	F2024-050	270.00	48.60	318.60	2024-06-06	\N	2024-06-06 09:10:00
6	3	2	F2024-060	200.00	36.00	236.00	2024-04-05	\N	2024-04-05 09:50:00
7	22	2	F2024-070	150.00	27.00	177.00	2024-05-07	\N	2024-05-07 10:40:00
8	12	17	F2024-080	180.00	32.40	212.40	2024-03-20	\N	2024-03-20 11:00:00
9	25	3	F2024-090	90.00	16.20	106.20	2024-04-07	\N	2024-04-07 12:10:00
10	7	19	F2024-100	600.00	108.00	708.00	2024-02-06	\N	2024-02-06 13:20:00
11	13	17	F2024-110	110.00	19.80	129.80	2024-03-04	\N	2024-03-04 09:05:00
12	20	2	F2024-120	750.00	135.00	885.00	2024-03-30	\N	2024-03-30 10:10:00
13	18	17	F2024-130	520.00	93.60	613.60	2024-01-11	\N	2024-01-11 11:50:00
14	4	17	F2024-140	400.00	72.00	472.00	2024-02-25	\N	2024-02-25 09:40:00
15	6	17	F2024-150	320.00	57.60	377.60	2024-02-23	\N	2024-02-23 10:30:00
16	8	2	F2024-160	120.00	21.60	141.60	2024-05-08	\N	2024-05-08 11:45:00
17	25	3	F2024-170	90.00	16.20	106.20	2024-04-06	\N	2024-04-06 12:00:00
18	2	17	F2024-180	240.00	43.20	283.20	2024-01-18	\N	2024-01-18 09:00:00
19	11	19	F2024-190	200.00	36.00	236.00	2024-03-12	\N	2024-03-12 10:00:00
20	23	17	F2024-200	150.00	27.00	177.00	2024-03-08	\N	2024-03-08 11:10:00
21	16	17	F2024-210	80.00	14.40	94.40	2024-03-04	\N	2024-03-04 12:00:00
22	1	17	F2024-220	220.00	39.60	259.60	2024-02-10	\N	2024-02-10 09:25:00
23	5	17	F2024-230	160.00	28.80	188.80	2024-01-12	\N	2024-01-12 10:15:00
24	24	2	F2024-240	50.00	9.00	59.00	2024-04-06	\N	2024-04-06 10:55:00
25	14	17	F2024-250	180.00	32.40	212.40	2024-04-04	\N	2024-04-04 11:40:00
\.


--
-- TOC entry 5099 (class 0 OID 57648)
-- Dependencies: 222
-- Data for Name: productos; Type: TABLE DATA; Schema: public; Owner: fvelasquezl
--

COPY public.productos (id_producto, nombre, descripcion, id_categoria, id_empresa_fabricante, precio_venta, stock_actual, stock_minimo, activo) FROM stdin;
2	Ibuprofeno 400 mg (blister 20)	Antiinflamatorio no esteroideo	3	9	12.00	270	15	t
5	Ceftriaxona 1 g frasco (inyectable)	Antibiótico de amplio espectro inyectable	5	15	40.00	80	5	t
6	Insulina Humana NPH 100 UI/ml 10ml	Insulina intermedia para diabetes	6	21	75.00	60	5	t
8	Guantes Nitrilo talla M (caja 100)	Guantes desechables sin polvo	8	22	55.00	400	50	t
9	Mascarilla quirúrgica 3 capas (caja 50)	Mascarillas desechables	8	20	35.00	1000	100	t
10	Solución Salina 0.9% 500 ml	Líquido para perfusión y lavado	10	12	12.50	300	30	t
11	Electrodos ECG (par)	Electrodos adhesivos para ECG	22	7	18.00	120	10	t
12	Termómetro digital clínico	Termómetro digital para uso clínico	9	11	45.00	80	10	t
13	Estetoscopio adulto	Estetoscopio doble campana	9	7	120.00	40	5	t
14	Sistema IV set (administración)	Equipo IV estéril	10	25	6.00	600	50	t
15	Sutura vicryl 4/0 (unidad)	Sutura absorbible para cirugía	11	19	15.00	150	10	t
16	Alcohol isopropílico 70% 1L	Antiséptico tópico y limpieza	4	13	10.00	400	40	t
17	Clorhexidina 2% 500 ml	Antiséptico para piel	4	2	22.00	200	20	t
18	Metformina 850 mg (blister 30)	Antidiabético oral	14	23	28.00	300	15	t
19	Omeprazol 20 mg (caja 14)	Inhibidor de bomba de protones	19	6	18.50	220	12	t
20	Nebulizador portátil	Equipo para terapia respiratoria	20	8	320.00	25	2	t
21	Oxígeno portátil 2L (cilindro pequeño)	Equipo de soporte respiratorio	15	18	950.00	10	1	t
22	Kit PCR COVID-19 (10 pruebas)	Reactivos y consumibles para PCR	22	4	450.00	20	2	t
23	Vitamina B12 1000 mcg ampolla	Suplemento inyectable	18	12	20.00	100	8	t
24	Cinta adhesiva quirúrgica 2.5 cm	Cinta médica para apósitos	8	25	4.50	300	30	t
25	Aspirina 100 mg masticable (caja 30)	Antiagregante y analgésico	23	21	30.00	120	10	t
3	Amoxicilina 500 mg (blister 16)	Antibiótico penicilina - 16 cap	2	4	25.00	200	10	t
1	Paracetamol 500 mg (blister 20)	Analgésico y antipirético - caja 20 comprimidos	1	6	8.50	500	20	f
4	Azitromicina 500 mg (caja 4)	Antibiótico macrólido - 3 comprimidos	2	1	35.00	150	8	t
26	paltomiel jarabe	500ml jarabe	1	2	12.50	20	2	t
7	Jeringa 5 ml con aguja 21G (unidad)	Jeringa estéril con aguja	8	3	1.50	1998	200	t
\.


--
-- TOC entry 5101 (class 0 OID 57670)
-- Dependencies: 224
-- Data for Name: lotes; Type: TABLE DATA; Schema: public; Owner: fvelasquezl
--

COPY public.lotes (id_lote, id_producto, id_empresa_proveedor, numero_lote, fecha_fabricacion, fecha_vencimiento, precio_compra, cantidad_inicial, activo, fecha_ingreso) FROM stdin;
1	1	2	BM202401A	2024-01-05	2026-01-05	3.50	500	t	2025-12-04
4	4	1	AZ202402D	2024-02-20	2026-02-20	18.00	150	t	2025-12-04
5	5	15	CEF202401E	2024-01-10	2025-01-10	22.00	80	t	2025-12-04
6	6	21	INS202406F	2024-06-01	2025-12-01	45.00	60	t	2025-12-04
8	8	22	GNT202405H	2024-05-04	2027-05-04	30.00	400	t	2025-12-04
9	9	20	MASK202404I	2024-04-15	2026-04-15	12.00	1000	t	2025-12-04
10	10	12	SS202403J	2024-03-10	2026-03-10	6.00	300	t	2025-12-04
11	11	7	ECG202402K	2024-02-01	2029-02-01	9.00	120	t	2025-12-04
12	12	11	TERM202403L	2024-03-15	2029-03-15	25.00	80	t	2025-12-04
13	13	7	EST202401M	2024-01-20	2030-01-20	70.00	40	t	2025-12-04
14	14	25	IV202404N	2024-04-05	2028-04-05	3.00	600	t	2025-12-04
15	15	19	SUT202402O	2024-02-18	2029-02-18	6.50	150	t	2025-12-04
16	16	13	ALC202403P	2024-03-02	2027-03-02	3.50	400	t	2025-12-04
17	17	2	CLX202401Q	2024-01-12	2026-01-12	10.00	200	t	2025-12-04
18	18	23	MET202404R	2024-04-01	2027-04-01	9.00	300	t	2025-12-04
19	19	6	OME202402S	2024-02-20	2026-02-20	7.50	220	t	2025-12-04
20	20	8	NEB202403T	2024-03-25	2030-03-25	200.00	25	t	2025-12-04
21	21	18	OX202401U	2024-01-05	2034-01-05	600.00	10	t	2025-12-04
22	22	4	PCR202402V	2024-02-28	2025-08-28	300.00	20	t	2025-12-04
23	23	12	B12-202403W	2024-03-05	2026-03-05	8.00	100	t	2025-12-04
24	24	25	CINT202404X	2024-04-01	2029-04-01	1.50	300	t	2025-12-04
25	25	21	ASA202402Y	2024-02-10	2026-02-10	10.00	120	t	2025-12-04
3	3	4	AMX202312C	2023-12-15	2025-12-15	12.00	197	t	2025-12-04
7	7	3	JER202404G	2024-04-01	2028-04-01	0.60	1998	t	2025-12-04
2	2	9	IB202403B	2024-03-02	2026-03-02	5.00	280	t	2025-12-04
\.


--
-- TOC entry 5109 (class 0 OID 57728)
-- Dependencies: 232
-- Data for Name: detalle_compras; Type: TABLE DATA; Schema: public; Owner: fvelasquezl
--

COPY public.detalle_compras (id_detalle_compra, id_compra, id_lote, cantidad) FROM stdin;
1	1	1	400
2	2	2	250
3	3	3	180
4	4	5	70
5	5	6	50
6	6	7	800
7	7	11	100
8	8	10	150
9	9	14	200
10	10	13	30
11	11	16	200
12	12	20	20
13	13	21	5
14	14	22	12
15	15	1	100
16	16	20	10
17	17	24	100
18	18	17	150
19	19	12	60
20	20	23	40
21	21	16	120
22	22	4	80
23	23	5	60
24	24	24	80
25	25	14	200
\.


--
-- TOC entry 5111 (class 0 OID 57745)
-- Dependencies: 234
-- Data for Name: ventas; Type: TABLE DATA; Schema: public; Owner: fvelasquezl
--

COPY public.ventas (id_venta, id_cliente, id_usuario, subtotal, descuento, total, fecha_venta) FROM stdin;
2	2	5	120.00	5.00	115.00	2024-02-03 10:00:00
3	3	4	300.00	0.00	300.00	2024-02-05 11:00:00
4	4	14	20.00	0.00	20.00	2024-02-07 12:00:00
5	5	4	80.00	10.00	70.00	2024-02-10 13:00:00
6	6	5	25.00	0.00	25.00	2024-02-12 09:30:00
7	7	4	200.00	0.00	200.00	2024-02-15 10:40:00
8	8	5	15.00	0.00	15.00	2024-02-18 11:20:00
9	9	4	400.00	20.00	380.00	2024-02-20 12:30:00
10	10	14	60.00	0.00	60.00	2024-02-22 14:00:00
11	11	4	220.00	0.00	220.00	2024-03-01 09:10:00
12	12	5	18.00	0.00	18.00	2024-03-03 09:30:00
13	13	4	120.00	5.00	115.00	2024-03-05 10:20:00
14	14	5	30.00	0.00	30.00	2024-03-08 11:00:00
15	15	4	90.00	10.00	80.00	2024-03-10 11:30:00
16	16	5	14.00	0.00	14.00	2024-03-12 12:00:00
17	17	4	55.00	0.00	55.00	2024-03-15 09:05:00
18	18	5	500.00	50.00	450.00	2024-03-18 10:45:00
19	19	4	35.00	0.00	35.00	2024-03-20 11:25:00
20	20	5	260.00	0.00	260.00	2024-03-22 12:10:00
21	21	4	19.00	0.00	19.00	2024-03-25 09:40:00
22	22	5	8.00	0.00	8.00	2024-03-28 10:15:00
23	23	4	45.00	0.00	45.00	2024-03-30 11:00:00
24	24	5	300.00	30.00	270.00	2024-04-02 12:30:00
25	25	4	12.00	0.00	12.00	2024-04-05 09:50:00
26	1	1	75.00	0.00	75.00	2025-12-05 23:37:37.412268
27	1	1	24.00	0.00	24.00	2025-12-05 23:48:23.149649
28	1	1	27.00	0.00	27.00	2025-12-06 10:17:52.18955
29	1	1	24.00	0.00	24.00	2025-12-06 14:21:34.006006
30	1	1	48.00	0.00	48.00	2025-12-10 10:36:38.188788
31	1	1	120.00	0.00	120.00	2025-12-10 12:16:32.237812
\.


--
-- TOC entry 5113 (class 0 OID 57764)
-- Dependencies: 236
-- Data for Name: detalle_ventas; Type: TABLE DATA; Schema: public; Owner: fvelasquezl
--

COPY public.detalle_ventas (id_detalle, id_venta, id_lote, cantidad, precio_unitario, descuento_aplicado) FROM stdin;
2	2	2	10	12.00	5.00
3	3	22	1	450.00	0.00
4	4	24	2	4.50	0.00
5	5	8	2	55.00	10.00
6	6	7	5	1.50	0.00
7	7	13	1	120.00	0.00
8	8	24	1	4.50	0.00
9	9	21	1	950.00	20.00
10	10	12	1	45.00	0.00
11	11	11	10	18.00	0.00
12	12	1	2	8.50	0.00
13	13	3	8	12.00	5.00
14	14	14	5	6.00	0.00
15	15	15	6	15.00	10.00
16	16	24	3	4.50	0.00
17	17	23	1	20.00	0.00
18	18	20	1	320.00	50.00
19	19	16	2	10.00	0.00
20	20	9	8	35.00	0.00
21	21	16	1	10.00	0.00
22	22	7	2	1.50	0.00
23	23	23	2	20.00	0.00
24	24	22	1	450.00	30.00
25	25	25	1	30.00	0.00
26	26	3	3	25.00	0.00
27	27	2	2	12.00	0.00
28	28	2	2	12.00	0.00
29	28	7	2	1.50	0.00
30	29	2	2	12.00	0.00
31	30	2	4	12.00	0.00
33	31	2	10	12.00	0.00
\.


--
-- TOC entry 5120 (class 0 OID 74084)
-- Dependencies: 243
-- Data for Name: directorio_clientes; Type: TABLE DATA; Schema: public; Owner: fvelasquezl
--

COPY public.directorio_clientes (dni, nombre_completo, telefono, email) FROM stdin;
12345678	Ana Pérez	944123456	ana.perez@mail.com
23456789	Luis García	944223456	luis.garcia@mail.com
34567890	María Rodríguez	944323456	maria.rodriguez@mail.com
45678901	Carlos Santos	944423456	carlos.santos@mail.com
56789012	Sofía Mendoza	944523456	sofia.mendoza@mail.com
67890123	Pedro Alvarez	944623456	pedro.alvarez@mail.com
78901234	Lucía Torres	944723456	lucia.torres@mail.com
89012345	Diego Ramos	944823456	diego.ramos@mail.com
90123456	Paola Vargas	944923456	paola.vargas@mail.com
01234567	Javier Cruz	945023456	javier.cruz@mail.com
11223344	Elena Suarez	945123456	elena.suarez@mail.com
22334455	Miguel Flores	945223456	miguel.flores@mail.com
33445566	Rosa Navarro	945323456	rosa.navarro@mail.com
44556677	Andrés Ruiz	945423456	andres.ruiz@mail.com
55667788	Carla Vega	945523456	carla.vega@mail.com
66778899	Bruno Castillo	945623456	bruno.castillo@mail.com
77889900	Marta Quispe	945723456	marta.quispe@mail.com
88990011	Hugo Lopez	945823456	hugo.lopez@mail.com
99001122	Julia Ramos	945923456	julia.ramos@mail.com
10101010	Ronaldo Gómez	946023456	ronaldo.gomez@mail.com
12121212	Nadia Benítez	946123456	nadia.benitez@mail.com
13131313	Óscar Paz	946223456	oscar.paz@mail.com
14141414	Isabel Chávez	946323456	isabel.chavez@mail.com
15151515	Tony Velásquez	946423456	tony.velasquez@mail.com
16161616	Verónica Huamán	946523456	veronica.huaman@mail.com
\.


--
-- TOC entry 5123 (class 0 OID 74097)
-- Dependencies: 246
-- Data for Name: historial_compras_clientes; Type: TABLE DATA; Schema: public; Owner: fvelasquezl
--

COPY public.historial_compras_clientes (fecha_venta, id_venta, cliente, monto_pagado) FROM stdin;
2024-04-05 09:50:00	25	Verónica Huamán	12.00
2024-04-02 12:30:00	24	Tony Velásquez	270.00
2024-03-30 11:00:00	23	Isabel Chávez	45.00
2024-03-28 10:15:00	22	Óscar Paz	8.00
2024-03-25 09:40:00	21	Nadia Benítez	19.00
2024-03-22 12:10:00	20	Ronaldo Gómez	260.00
2024-03-20 11:25:00	19	Julia Ramos	35.00
2024-03-18 10:45:00	18	Hugo Lopez	450.00
2024-03-15 09:05:00	17	Marta Quispe	55.00
2024-03-12 12:00:00	16	Bruno Castillo	14.00
2024-03-10 11:30:00	15	Carla Vega	80.00
2024-03-08 11:00:00	14	Andrés Ruiz	30.00
2024-03-05 10:20:00	13	Rosa Navarro	115.00
2024-03-03 09:30:00	12	Miguel Flores	18.00
2024-03-01 09:10:00	11	Elena Suarez	220.00
2024-02-22 14:00:00	10	Javier Cruz	60.00
2024-02-20 12:30:00	9	Paola Vargas	380.00
2024-02-18 11:20:00	8	Diego Ramos	15.00
2024-02-15 10:40:00	7	Lucía Torres	200.00
2024-02-12 09:30:00	6	Pedro Alvarez	25.00
2024-02-10 13:00:00	5	Sofía Mendoza	70.00
2024-02-07 12:00:00	4	Carlos Santos	20.00
2024-02-05 11:00:00	3	María Rodríguez	300.00
2024-02-03 10:00:00	2	Luis García	115.00
2024-02-01 09:00:00	1	Ana Pérez	50.00
\.


--
-- TOC entry 5115 (class 0 OID 57782)
-- Dependencies: 238
-- Data for Name: promociones; Type: TABLE DATA; Schema: public; Owner: fvelasquezl
--

COPY public.promociones (id_promocion, nombre, descripcion, tipo_descuento, valor_descuento, fecha_inicio, fecha_fin, activo) FROM stdin;
1	Promo Verano 2024	10% en guantes nitrilo y mascarillas	porcentaje	10.00	2024-03-01	2024-04-30	t
2	Equipos - Descuento por Bundle	S/50 de descuento en combos de equipos (nebulizador+mascarilla)	monto	50.00	2024-02-15	2024-05-31	t
3	Campaña Salud Pública	5% en antibióticos seleccionados	porcentaje	5.00	2024-01-10	2024-06-30	t
4	Promo Laboratorio	S/30 en kits PCR (compra mínima 1)	monto	30.00	2024-02-20	2024-04-30	t
\.


--
-- TOC entry 5117 (class 0 OID 57792)
-- Dependencies: 240
-- Data for Name: producto_promocion; Type: TABLE DATA; Schema: public; Owner: fvelasquezl
--

COPY public.producto_promocion (id_producto_promocion, id_producto, id_promocion) FROM stdin;
1	8	1
2	9	1
3	20	2
4	22	4
\.


--
-- TOC entry 5119 (class 0 OID 74079)
-- Dependencies: 242
-- Data for Name: productos_info; Type: TABLE DATA; Schema: public; Owner: fvelasquezl
--

COPY public.productos_info (id_producto, nombre, descripcion, precio_venta, stock_actual) FROM stdin;
1	Paracetamol 500 mg (blister 20)	Analgésico y antipirético - caja 20 comprimidos	8.50	200
2	Ibuprofeno 400 mg (blister 20)	Antiinflamatorio no esteroideo	12.00	150
3	Amoxicilina 500 mg (blister 16)	Antibiótico penicilina - 16 caps	25.00	120
4	Azitromicina 500 mg (caja 3)	Antibiótico macrólido - 3 comprimidos	35.00	80
5	Ceftriaxona 1 g frasco (inyectable)	Antibiótico de amplio espectro inyectable	40.00	50
6	Insulina Humana NPH 100 UI/ml 10ml	Insulina intermedia para diabetes	75.00	40
7	Jeringa 5 ml con aguja 21G (unidad)	Jeringa estéril con aguja	1.50	1000
8	Guantes Nitrilo talla M (caja 100)	Guantes desechables sin polvo	55.00	300
9	Mascarilla quirúrgica 3 capas (caja 50)	Mascarillas desechables	35.00	500
10	Solución Salina 0.9% 500 ml	Líquido para perfusión y lavado	12.50	180
11	Electrodos ECG (par)	Electrodos adhesivos para ECG	18.00	60
12	Termómetro digital clínico	Termómetro digital para uso clínico	45.00	70
13	Estetoscopio adulto	Estetoscopio doble campana	120.00	25
14	Sistema IV set (administración)	Equipo IV estéril	6.00	400
15	Sutura vicryl 4/0 (unidad)	Sutura absorbible para cirugía	15.00	90
16	Alcohol isopropílico 70% 1L	Antiséptico tópico y limpieza	10.00	250
17	Clorhexidina 2% 500 ml	Antiséptico para piel	22.00	120
18	Metformina 850 mg (blister 30)	Antidiabético oral	28.00	160
19	Omeprazol 20 mg (caja 14)	Inhibidor de bomba de protones	18.50	140
20	Nebulizador portátil	Equipo para terapia respiratoria	320.00	18
21	Oxígeno portátil 2L (cilindro pequeño)	Equipo de soporte respiratorio	950.00	8
22	Kit PCR COVID-19 (10 pruebas)	Reactivos y consumibles para PCR	450.00	12
23	Vitamina B12 1000 mcg ampolla	Suplemento inyectable	20.00	75
24	Cinta adhesiva quirúrgica 2.5 cm	Cinta médica para apósitos	4.50	220
25	Aspirina 100 mg masticable (caja 30)	Antiagregante y analgésico	30.00	90
\.


--
-- TOC entry 5118 (class 0 OID 74056)
-- Dependencies: 241
-- Data for Name: productos_info_basica; Type: TABLE DATA; Schema: public; Owner: fvelasquezl
--

COPY public.productos_info_basica (id_producto, nombre, descripcion, precio_venta) FROM stdin;
\.


--
-- TOC entry 5121 (class 0 OID 74089)
-- Dependencies: 244
-- Data for Name: reporte_vencimientos; Type: TABLE DATA; Schema: public; Owner: fvelasquezl
--

COPY public.reporte_vencimientos (numero_lote, nombre_producto, fecha_vencimiento, cantidad_lote) FROM stdin;
CEF202401E	Ceftriaxona 1 g frasco (inyectable)	2025-01-10	80
PCR202402V	Kit PCR COVID-19 (10 pruebas)	2025-08-28	20
INS202406F	Insulina Humana NPH 100 UI/ml 10ml	2025-12-01	60
AMX202312C	Amoxicilina 500 mg (blister 16)	2025-12-15	200
BM202401A	Paracetamol 500 mg (blister 20)	2026-01-05	500
CLX202401Q	Clorhexidina 2% 500 ml	2026-01-12	200
ASA202402Y	Aspirina 100 mg masticable (caja 30)	2026-02-10	120
OME202402S	Omeprazol 20 mg (caja 14)	2026-02-20	220
AZ202402D	Azitromicina 500 mg (caja 3)	2026-02-20	150
IB202403B	Ibuprofeno 400 mg (blister 20)	2026-03-02	300
B12-202403W	Vitamina B12 1000 mcg ampolla	2026-03-05	100
SS202403J	Solución Salina 0.9% 500 ml	2026-03-10	300
MASK202404I	Mascarilla quirúrgica 3 capas (caja 50)	2026-04-15	1000
ALC202403P	Alcohol isopropílico 70% 1L	2027-03-02	400
MET202404R	Metformina 850 mg (blister 30)	2027-04-01	300
GNT202405H	Guantes Nitrilo talla M (caja 100)	2027-05-04	400
JER202404G	Jeringa 5 ml con aguja 21G (unidad)	2028-04-01	2000
IV202404N	Sistema IV set (administración)	2028-04-05	600
ECG202402K	Electrodos ECG (par)	2029-02-01	120
SUT202402O	Sutura vicryl 4/0 (unidad)	2029-02-18	150
TERM202403L	Termómetro digital clínico	2029-03-15	80
CINT202404X	Cinta adhesiva quirúrgica 2.5 cm	2029-04-01	300
EST202401M	Estetoscopio adulto	2030-01-20	40
NEB202403T	Nebulizador portátil	2030-03-25	25
OX202401U	Oxígeno portátil 2L (cilindro pequeño)	2034-01-05	10
\.


--
-- TOC entry 5122 (class 0 OID 74092)
-- Dependencies: 245
-- Data for Name: valor_inventario; Type: TABLE DATA; Schema: public; Owner: fvelasquezl
--

COPY public.valor_inventario (id_producto, nombre, categoria, stock_actual, precio_venta, valor_total_venta) FROM stdin;
1	Paracetamol 500 mg (blister 20)	Analgesicos	200	8.50	1700.00
4	Azitromicina 500 mg (caja 3)	Antibioticos	80	35.00	2800.00
3	Amoxicilina 500 mg (blister 16)	Antibioticos	120	25.00	3000.00
2	Ibuprofeno 400 mg (blister 20)	Antiinflamatorios	150	12.00	1800.00
17	Clorhexidina 2% 500 ml	Antisepticos	120	22.00	2640.00
16	Alcohol isopropílico 70% 1L	Antisepticos	250	10.00	2500.00
5	Ceftriaxona 1 g frasco (inyectable)	Inyectables	50	40.00	2000.00
6	Insulina Humana NPH 100 UI/ml 10ml	Insulina	40	75.00	3000.00
24	Cinta adhesiva quirúrgica 2.5 cm	Consumibles	220	4.50	990.00
9	Mascarilla quirúrgica 3 capas (caja 50)	Consumibles	500	35.00	17500.00
8	Guantes Nitrilo talla M (caja 100)	Consumibles	300	55.00	16500.00
7	Jeringa 5 ml con aguja 21G (unidad)	Consumibles	1000	1.50	1500.00
13	Estetoscopio adulto	Equipos Pequenos	25	120.00	3000.00
12	Termómetro digital clínico	Equipos Pequenos	70	45.00	3150.00
14	Sistema IV set (administración)	Líquidos Intravenosos	400	6.00	2400.00
10	Solución Salina 0.9% 500 ml	Líquidos Intravenosos	180	12.50	2250.00
15	Sutura vicryl 4/0 (unidad)	Suturas	90	15.00	1350.00
18	Metformina 850 mg (blister 30)	Antidiabeticos	160	28.00	4480.00
21	Oxígeno portátil 2L (cilindro pequeño)	Gases Medicinales	8	950.00	7600.00
23	Vitamina B12 1000 mcg ampolla	Vitaminas	75	20.00	1500.00
19	Omeprazol 20 mg (caja 14)	Digestivos	140	18.50	2590.00
20	Nebulizador portátil	Respiratorio	18	320.00	5760.00
22	Kit PCR COVID-19 (10 pruebas)	Laboratorio	12	450.00	5400.00
11	Electrodos ECG (par)	Laboratorio	60	18.00	1080.00
25	Aspirina 100 mg masticable (caja 30)	Cardiologia	90	30.00	2700.00
\.


--
-- TOC entry 5129 (class 0 OID 0)
-- Dependencies: 219
-- Name: categorias_id_categoria_seq; Type: SEQUENCE SET; Schema: public; Owner: fvelasquezl
--

SELECT pg_catalog.setval('public.categorias_id_categoria_seq', 25, true);


--
-- TOC entry 5130 (class 0 OID 0)
-- Dependencies: 225
-- Name: clientes_id_cliente_seq; Type: SEQUENCE SET; Schema: public; Owner: fvelasquezl
--

SELECT pg_catalog.setval('public.clientes_id_cliente_seq', 25, true);


--
-- TOC entry 5131 (class 0 OID 0)
-- Dependencies: 229
-- Name: compras_id_compra_seq; Type: SEQUENCE SET; Schema: public; Owner: fvelasquezl
--

SELECT pg_catalog.setval('public.compras_id_compra_seq', 25, true);


--
-- TOC entry 5132 (class 0 OID 0)
-- Dependencies: 231
-- Name: detalle_compras_id_detalle_compra_seq; Type: SEQUENCE SET; Schema: public; Owner: fvelasquezl
--

SELECT pg_catalog.setval('public.detalle_compras_id_detalle_compra_seq', 25, true);


--
-- TOC entry 5133 (class 0 OID 0)
-- Dependencies: 235
-- Name: detalle_ventas_id_detalle_seq; Type: SEQUENCE SET; Schema: public; Owner: fvelasquezl
--

SELECT pg_catalog.setval('public.detalle_ventas_id_detalle_seq', 33, true);


--
-- TOC entry 5134 (class 0 OID 0)
-- Dependencies: 217
-- Name: empresa_id_empresa_seq; Type: SEQUENCE SET; Schema: public; Owner: fvelasquezl
--

SELECT pg_catalog.setval('public.empresa_id_empresa_seq', 25, true);


--
-- TOC entry 5135 (class 0 OID 0)
-- Dependencies: 223
-- Name: lotes_id_lote_seq; Type: SEQUENCE SET; Schema: public; Owner: fvelasquezl
--

SELECT pg_catalog.setval('public.lotes_id_lote_seq', 25, true);


--
-- TOC entry 5136 (class 0 OID 0)
-- Dependencies: 239
-- Name: producto_promocion_id_producto_promocion_seq; Type: SEQUENCE SET; Schema: public; Owner: fvelasquezl
--

SELECT pg_catalog.setval('public.producto_promocion_id_producto_promocion_seq', 4, true);


--
-- TOC entry 5137 (class 0 OID 0)
-- Dependencies: 221
-- Name: productos_id_producto_seq; Type: SEQUENCE SET; Schema: public; Owner: fvelasquezl
--

SELECT pg_catalog.setval('public.productos_id_producto_seq', 26, true);


--
-- TOC entry 5138 (class 0 OID 0)
-- Dependencies: 237
-- Name: promociones_id_promocion_seq; Type: SEQUENCE SET; Schema: public; Owner: fvelasquezl
--

SELECT pg_catalog.setval('public.promociones_id_promocion_seq', 4, true);


--
-- TOC entry 5139 (class 0 OID 0)
-- Dependencies: 227
-- Name: usuarios_id_usuario_seq; Type: SEQUENCE SET; Schema: public; Owner: fvelasquezl
--

SELECT pg_catalog.setval('public.usuarios_id_usuario_seq', 27, true);


--
-- TOC entry 5140 (class 0 OID 0)
-- Dependencies: 233
-- Name: ventas_id_venta_seq; Type: SEQUENCE SET; Schema: public; Owner: fvelasquezl
--

SELECT pg_catalog.setval('public.ventas_id_venta_seq', 31, true);


-- Completed on 2026-05-15 23:17:59

--
-- PostgreSQL database dump complete
--

\unrestrict lIsyJLY0kMn8u2kisb6PIfdRig6I6CTaSgS93U2oUwZXedOhEimYkWXx9ILY4qW

