/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

/*!40000 ALTER TABLE "categoria" DISABLE KEYS */;
INSERT INTO "categoria" ("id", "nome") VALUES
	(1, 'Informática'),
	(2, 'Escritório'),
	(3, 'Cama, Mesa e Banho'),
	(4, 'Eletrônicos'),
	(5, 'Jardinagem'),
	(6, 'Decoração'),
	(7, 'Perfumaria');
/*!40000 ALTER TABLE "categoria" ENABLE KEYS */;

/*!40000 ALTER TABLE "categorias_do_produto" DISABLE KEYS */;
INSERT INTO "categorias_do_produto" ("produto_id", "categoria_id") VALUES
	(1, 1),
	(1, 4),
	(2, 1),
	(2, 2),
	(2, 4),
	(3, 1),
	(3, 4),
	(4, 2),
	(5, 3),
	(6, 3),
	(7, 4),
	(8, 5),
	(9, 6),
	(10, 6),
	(11, 7);
/*!40000 ALTER TABLE "categorias_do_produto" ENABLE KEYS */;

/*!40000 ALTER TABLE "cidade" DISABLE KEYS */;
INSERT INTO "cidade" ("id", "nome", "estado_id") VALUES
	(1, 'Uberlândia', 1),
	(2, 'São Paulo', 2),
	(3, 'Campinas', 2);
/*!40000 ALTER TABLE "cidade" ENABLE KEYS */;

/*!40000 ALTER TABLE "cliente" DISABLE KEYS */;
INSERT INTO "cliente" ("id", "cpf_ou_cnpj", "email", "nome", "pessoa") VALUES
	(1, '304.714.108-88', 'maria@gmail.com', 'Maria Silva', 1);
/*!40000 ALTER TABLE "cliente" ENABLE KEYS */;

/*!40000 ALTER TABLE "endereco" DISABLE KEYS */;
INSERT INTO "endereco" ("id", "bairro", "cep", "complemento", "logradouro", "numero", "cidade_id", "cliente_id") VALUES
	(1, 'Jd Helena', '05271160', 'Apto 303', 'Rua Flores', '300', 1, 1),
	(2, 'Centro', '13930000', NULL, 'Av. Matos Soares', '105', 2, 1);
/*!40000 ALTER TABLE "endereco" ENABLE KEYS */;

/*!40000 ALTER TABLE "estado" DISABLE KEYS */;
INSERT INTO "estado" ("id", "nome") VALUES
	(1, 'MG'),
	(2, 'SP');
/*!40000 ALTER TABLE "estado" ENABLE KEYS */;

/*!40000 ALTER TABLE "item_pedido" DISABLE KEYS */;
INSERT INTO "item_pedido" ("desconto", "preco", "quantidade", "pedido_id", "produto_id") VALUES
	(0, 2000, 1, 1, 1),
	(0, 80, 2, 1, 3),
	(100, 800, 1, 2, 2);
/*!40000 ALTER TABLE "item_pedido" ENABLE KEYS */;

/*!40000 ALTER TABLE "pagamento" DISABLE KEYS */;
INSERT INTO "pagamento" ("pedido_id", "estado") VALUES
	(1, 2),
	(2, 1);
/*!40000 ALTER TABLE "pagamento" ENABLE KEYS */;

/*!40000 ALTER TABLE "pagamento_com_boleto" DISABLE KEYS */;
INSERT INTO "pagamento_com_boleto" ("data_pagamento", "data_vencimento", "pedido_id") VALUES
	(NULL, '2017-10-20 02:00:00', 2);
/*!40000 ALTER TABLE "pagamento_com_boleto" ENABLE KEYS */;

/*!40000 ALTER TABLE "pagamento_com_cartao" DISABLE KEYS */;
INSERT INTO "pagamento_com_cartao" ("numero_de_parcelas", "pedido_id") VALUES
	(6, 1);
/*!40000 ALTER TABLE "pagamento_com_cartao" ENABLE KEYS */;

/*!40000 ALTER TABLE "pedido" DISABLE KEYS */;
INSERT INTO "pedido" ("id", "criado_em", "cliente_id", "endereco_de_entrega_id") VALUES
	(1, '2017-09-30 13:32:00', 1, 1),
	(2, '2017-10-10 22:35:00', 1, 2);
/*!40000 ALTER TABLE "pedido" ENABLE KEYS */;

/*!40000 ALTER TABLE "produto" DISABLE KEYS */;
INSERT INTO "produto" ("id", "nome", "preco") VALUES
	(1, 'Computador', 2000),
	(2, 'Impressora', 800),
	(3, 'Mouse', 80),
	(4, 'Mesa de erscritório', 300),
	(5, 'Toalha', 50),
	(6, 'Colcha', 200),
	(7, 'TV True Color', 1200),
	(8, 'Roçadeira', 800),
	(9, 'Abajour', 100),
	(10, 'Pendente', 180),
	(11, 'Shampoo', 90);
/*!40000 ALTER TABLE "produto" ENABLE KEYS */;

/*!40000 ALTER TABLE "telefones" DISABLE KEYS */;
INSERT INTO "telefones" ("cliente_id", "telefones") VALUES
	(1, '1996707272'),
	(1, '19997014416');
/*!40000 ALTER TABLE "telefones" ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
