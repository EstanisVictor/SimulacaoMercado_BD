PGDMP     '    
                 z         	   mercearia    13.4    13.4     ?           0    0    ENCODING    ENCODING         SET client_encoding = 'LATIN1';
                      false            ?           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            ?           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            ?           1262    24576 	   mercearia    DATABASE     i   CREATE DATABASE mercearia WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Portuguese_Brazil.1252';
    DROP DATABASE mercearia;
                postgres    false            ?            1259    24579    cliente    TABLE     ?   CREATE TABLE public.cliente (
    id bigint NOT NULL,
    nome character varying(256),
    endereeco character varying(1024),
    tel character varying(11)
);
    DROP TABLE public.cliente;
       public         heap    postgres    false            ?            1259    24577    cliente_id_seq    SEQUENCE     w   CREATE SEQUENCE public.cliente_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.cliente_id_seq;
       public          postgres    false    201            ?           0    0    cliente_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.cliente_id_seq OWNED BY public.cliente.id;
          public          postgres    false    200            ?            1259    24590    produto    TABLE     ?   CREATE TABLE public.produto (
    nomee character varying(128),
    preco double precision,
    quantidade integer,
    id bigint NOT NULL
);
    DROP TABLE public.produto;
       public         heap    postgres    false            ?            1259    24609    produto_id_seq    SEQUENCE     w   CREATE SEQUENCE public.produto_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.produto_id_seq;
       public          postgres    false    202            ?           0    0    produto_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.produto_id_seq OWNED BY public.produto.id;
          public          postgres    false    205            ?            1259    24617    produtovendido    TABLE     ?   CREATE TABLE public.produtovendido (
    id_venda bigint NOT NULL,
    id_produto bigint NOT NULL,
    quantidade integer,
    precodia double precision
);
 "   DROP TABLE public.produtovendido;
       public         heap    postgres    false            ?            1259    24596    venda    TABLE     ?   CREATE TABLE public.venda (
    id bigint NOT NULL,
    dia timestamp without time zone,
    total double precision,
    id_usuario bigint
);
    DROP TABLE public.venda;
       public         heap    postgres    false            ?            1259    24594    venda_id_seq    SEQUENCE     u   CREATE SEQUENCE public.venda_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.venda_id_seq;
       public          postgres    false    204            ?           0    0    venda_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.venda_id_seq OWNED BY public.venda.id;
          public          postgres    false    203            3           2604    24582 
   cliente id    DEFAULT     h   ALTER TABLE ONLY public.cliente ALTER COLUMN id SET DEFAULT nextval('public.cliente_id_seq'::regclass);
 9   ALTER TABLE public.cliente ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    201    200    201            4           2604    24611 
   produto id    DEFAULT     h   ALTER TABLE ONLY public.produto ALTER COLUMN id SET DEFAULT nextval('public.produto_id_seq'::regclass);
 9   ALTER TABLE public.produto ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    205    202            5           2604    24599    venda id    DEFAULT     d   ALTER TABLE ONLY public.venda ALTER COLUMN id SET DEFAULT nextval('public.venda_id_seq'::regclass);
 7   ALTER TABLE public.venda ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    204    203    204            ?          0    24579    cliente 
   TABLE DATA           ;   COPY public.cliente (id, nome, endereeco, tel) FROM stdin;
    public          postgres    false    201   E       ?          0    24590    produto 
   TABLE DATA           ?   COPY public.produto (nomee, preco, quantidade, id) FROM stdin;
    public          postgres    false    202   ?       ?          0    24617    produtovendido 
   TABLE DATA           T   COPY public.produtovendido (id_venda, id_produto, quantidade, precodia) FROM stdin;
    public          postgres    false    206   ?!       ?          0    24596    venda 
   TABLE DATA           ;   COPY public.venda (id, dia, total, id_usuario) FROM stdin;
    public          postgres    false    204   ?!       ?           0    0    cliente_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.cliente_id_seq', 5, true);
          public          postgres    false    200            ?           0    0    produto_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.produto_id_seq', 28, true);
          public          postgres    false    205            ?           0    0    venda_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.venda_id_seq', 4, true);
          public          postgres    false    203            ;           2606    24601    venda chaveprimaria 
   CONSTRAINT     Q   ALTER TABLE ONLY public.venda
    ADD CONSTRAINT chaveprimaria PRIMARY KEY (id);
 =   ALTER TABLE ONLY public.venda DROP CONSTRAINT chaveprimaria;
       public            postgres    false    204            7           2606    24587    cliente cliente_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.cliente DROP CONSTRAINT cliente_pkey;
       public            postgres    false    201            9           2606    24616    produto produto_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.produto
    ADD CONSTRAINT produto_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.produto DROP CONSTRAINT produto_pkey;
       public            postgres    false    202            =           2606    24621 "   produtovendido produtovendido_pkey 
   CONSTRAINT     r   ALTER TABLE ONLY public.produtovendido
    ADD CONSTRAINT produtovendido_pkey PRIMARY KEY (id_venda, id_produto);
 L   ALTER TABLE ONLY public.produtovendido DROP CONSTRAINT produtovendido_pkey;
       public            postgres    false    206    206            ?           2606    24627    produtovendido chavendafk    FK CONSTRAINT     ?   ALTER TABLE ONLY public.produtovendido
    ADD CONSTRAINT chavendafk FOREIGN KEY (id_venda) REFERENCES public.venda(id) NOT VALID;
 C   ALTER TABLE ONLY public.produtovendido DROP CONSTRAINT chavendafk;
       public          postgres    false    2875    206    204            >           2606    24622    produtovendido chaveprodutofk    FK CONSTRAINT     ?   ALTER TABLE ONLY public.produtovendido
    ADD CONSTRAINT chaveprodutofk FOREIGN KEY (id_produto) REFERENCES public.produto(id);
 G   ALTER TABLE ONLY public.produtovendido DROP CONSTRAINT chaveprodutofk;
       public          postgres    false    2873    206    202            ?   {   x?]?A!E?p?^@3F?e?i"??????C?N????=o?io, X0??HGxfl?"? ba?\?A??xwۮc??l0;Jf?L????h>??-kv??iÚ4c??AG?4:???????&&      ?   ?  x?MQ?n?0???
}!??QM??C??-ӖR?4h?w??"Cй???:h9?y?}8??.^??oR^hIΑ?O?yY??c??k ?E?I??凜???????T??<ms?y?icI?<??_;??L?9??~??3|pߓIjޅ9???IJRlû?-?e??j$?E????)?kY???b?cHI???#\{`
?kE???qiF=????Axb?@'u?!o:??????s?[S???? ???ǗZB???m4$-??Fvh???<O??gHW?H
(???!H
F?'?%?;????Xc9G?@F?J$??5?bm?6x?cp4?C8?"?- ??x?????^xϸ?F0???~	?k??b?"??????BZ?ς?,?-?:?hu?)?M????~?%????`??qu??      ?   ^   x?M??1cQc??M/?/??V?$?}??E??wOha?11]yN?}?\?D,I???u=[xG*??-?(?%>???0??n??s3?'k8      ?   ^   x?]ͱQ???? ??1K??#??.n?<?!?W??|E/???AA??2K_/#F:?????x?{????F??????{g?|?6??c?????     