
-- 1. USUARIOS
INSERT INTO usuario (id, nome, login, senha, tipo_usuario) VALUES (1, 'Gerente Geral', 'gerente', '12345', 'GERENTE');
INSERT INTO usuario (id, nome, login, senha, tipo_usuario) VALUES (2, 'Caixa Principal', 'caixa', '12345', 'CAIXA');
INSERT INTO usuario (id, nome, login, senha, tipo_usuario) VALUES (3, 'Garçom João', 'joao', '12345', 'GARCOM');
INSERT INTO usuario (id, nome, login, senha, tipo_usuario) VALUES (4, 'Garçom Maria', 'maria', '12345', 'GARCOM');
INSERT INTO usuario (id, nome, login, senha, tipo_usuario) VALUES (5, 'Cozinheiro Chefe', 'chefe', '12345', 'COZINHA');

-- 2. CARDAPIO
INSERT INTO cardapio (id, gerente_id) VALUES (1, 1);

-- 3. CATEGORIAS
INSERT INTO categoria (id, nome, cardapio_id) VALUES (1, 'Bebidas', 1);
INSERT INTO categoria (id, nome, cardapio_id) VALUES (2, 'Pratos Principais', 1);
INSERT INTO categoria (id, nome, cardapio_id) VALUES (3, 'Sobremesas', 1);
INSERT INTO categoria (id, nome, categoria_mae_id, cardapio_id) VALUES (4, 'Refrigerantes', 1, 1);
INSERT INTO categoria (id, nome, categoria_mae_id, cardapio_id) VALUES (5, 'Pratos Vegetarianos', 2, 1); -- NOVO: Subcategoria de Pratos Principais
INSERT INTO categoria (id, nome, cardapio_id) VALUES (6, 'Sanduíches', 1);
INSERT INTO categoria (id, nome, categoria_mae_id, cardapio_id) VALUES (7, 'Smoothies & Sucos', 1, 1);

-- 4. ITENS DE CARDAPIO
INSERT INTO item_cardapio (id, nome, ingredientes, preco, disponivel_na_cozinha, categoria_id)
VALUES (1, 'Coca-Cola Zero', 'Refrigerante 350ml', 5.50, TRUE, 4);
INSERT INTO item_cardapio (id, nome, ingredientes, preco, disponivel_na_cozinha, categoria_id)
VALUES (2, 'Berinjela à Parmegiana', 'Berinjela, molho de tomate, queijo, arroz e fritas', 45.00, TRUE, 5); -- Vegetariano
INSERT INTO item_cardapio (id, nome, ingredientes, preco, disponivel_na_cozinha, categoria_id)
VALUES (3, 'Petit Gateau', 'Bolo de chocolate quente com sorvete de creme', 22.00, TRUE, 3);
INSERT INTO item_cardapio (id, nome, ingredientes, preco, disponivel_na_cozinha, categoria_id)
VALUES (4, 'Café Expresso', 'Café 50ml', 4.00, TRUE, 1);
INSERT INTO item_cardapio (id, nome, ingredientes, preco, disponivel_na_cozinha, categoria_id)
VALUES (5, 'Hambúrguer Vegano', 'Pão integral, burger de grão-de-bico, salada, maionese vegana', 38.00, TRUE, 5); -- Vegetariano
INSERT INTO item_cardapio (id, nome, ingredientes, preco, disponivel_na_cozinha, categoria_id)
VALUES (6, 'Tacos de Jaca', 'Tortilhas, jaca desfiada em molho barbecue, coentro', 32.00, TRUE, 5); -- Vegetariano
INSERT INTO item_cardapio (id, nome, ingredientes, preco, disponivel_na_cozinha, categoria_id)
VALUES (7, 'Suco Verde Detox', 'Couve, limão, gengibre, maçã', 14.00, TRUE, 7); -- Suco/Smoothie
INSERT INTO item_cardapio (id, nome, ingredientes, preco, disponivel_na_cozinha, categoria_id)
VALUES (8, 'Smoothie Vermelho', 'Morango, banana, água de coco', 16.00, TRUE, 7); -- Suco/Smoothie
INSERT INTO item_cardapio (id, nome, ingredientes, preco, disponivel_na_cozinha, categoria_id)
VALUES (9, 'Sanduíche Clássico', 'Pão de forma, queijo prato, presunto, manteiga', 18.00, TRUE, 6); -- Sanduíche
INSERT INTO item_cardapio (id, nome, ingredientes, preco, disponivel_na_cozinha, categoria_id)
VALUES (10, 'Tiramisu', 'Mascarpone, café, biscoito champagne, cacau', 25.00, TRUE, 3); -- Sobremesa

-- 5. MESAS
INSERT INTO mesa (id, numero, disponivel, garcom_id) VALUES (1, 10, FALSE, 3);
INSERT INTO mesa (id, numero, disponivel, garcom_id) VALUES (2, 11, TRUE, 3);
INSERT INTO mesa (id, numero, disponivel, garcom_id) VALUES (3, 20, TRUE, 4);
INSERT INTO mesa (id, numero, disponivel, garcom_id) VALUES (4, 12, TRUE, 3);

-- 6. CLIENTES
INSERT INTO cliente (id, nome, hora_chegada, hora_saida) VALUES (1, 'Joana', CURRENT_TIMESTAMP(), NULL);
INSERT INTO cliente (id, nome, hora_chegada, hora_saida) VALUES (2, 'Julia', CURRENT_TIMESTAMP(), NULL);
INSERT INTO cliente (id, nome, hora_chegada, hora_saida) VALUES (3, 'Jéssica', CURRENT_TIMESTAMP(), NULL);

-- 7. CONTA
INSERT INTO conta (id, data_abertura, data_fechamento, valor_total, status, mesa_id) VALUES (1, CURRENT_TIMESTAMP(), NULL, 0.0, 'ABERTA', 1);
INSERT INTO pedido (id, numero, horario_pedido, horario_entrega, cliente_id, conta_id, status, valor_total) VALUES (1, 101, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 1, 1, 'ENTREGUE', 56.00);
-- Pedido de Joana: 2 Coca-Cola Zero (5.50*2=11) + 1 Berinjela à Parmegiana (45.00) = 56.00
INSERT INTO item_pedido (id, quantidade, item_cardapio_id, pedido_id) VALUES (1, 2.0, 1, 1);
INSERT INTO item_pedido (id, quantidade, item_cardapio_id, pedido_id) VALUES (2, 1.0, 2, 1);

-- 8. CONTA
INSERT INTO conta (id, data_abertura, data_fechamento, valor_total, status, mesa_id) VALUES (2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 22.00, 'FECHADA', 4);
INSERT INTO pedido (id, numero, horario_pedido, horario_entrega, cliente_id, conta_id, status, valor_total) VALUES (2, 102, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 2, 2, 'ENTREGUE', 22.00);
INSERT INTO item_pedido (id, quantidade, item_cardapio_id, pedido_id) VALUES (3, 1.0, 3, 2);
INSERT INTO pagamento (id, valor, conta_id, tipo_pagamento, nro_transacao, numero_cheque) VALUES (1, 22.00, 2, 'CARTAO', 'TRX123456789', NULL);

-- REINICIA OS CONTADORES DE CHAVE PRIMÁRIA
ALTER TABLE usuario ALTER COLUMN id RESTART WITH 6;
ALTER TABLE cardapio ALTER COLUMN id RESTART WITH 2;
ALTER TABLE categoria ALTER COLUMN id RESTART WITH 8;
ALTER TABLE item_cardapio ALTER COLUMN id RESTART WITH 11;
ALTER TABLE mesa ALTER COLUMN id RESTART WITH 5;
ALTER TABLE cliente ALTER COLUMN id RESTART WITH 4;
ALTER TABLE conta ALTER COLUMN id RESTART WITH 3;
ALTER TABLE pedido ALTER COLUMN id RESTART WITH 3;
ALTER TABLE item_pedido ALTER COLUMN id RESTART WITH 4;
ALTER TABLE pagamento ALTER COLUMN id RESTART WITH 2;

-- CRIAÇÃO DA VIEW PARA RELATORIO
CREATE VIEW V_RELATORIO AS
SELECT
    c.id AS id_conta,
    c.status AS status_conta,
    c.data_abertura,
    c.data_fechamento,
    c.valor_total AS valor_total_conta,
    m.numero AS numero_mesa,
    m.disponivel AS mesa_disponivel,
    u_garcom.nome AS nome_garcom,
    u_garcom.login AS login_garcom,
    p.tipo_pagamento,
    p.valor AS valor_pago,
    p.nro_transacao,
    p.numero_cheque,
    pd.id AS id_pedido,
    pd.numero AS numero_pedido,
    pd.status AS status_pedido,
    pd.valor_total AS valor_total_pedido,
    cl.nome AS nome_cliente,
    cl.hora_chegada AS cliente_chegada
FROM
    conta c
        LEFT JOIN
    mesa m ON c.mesa_id = m.id
        LEFT JOIN
    usuario u_garcom ON m.garcom_id = u_garcom.id
        LEFT JOIN
    pagamento p ON c.id = p.conta_id
        LEFT JOIN
    pedido pd ON c.id = pd.conta_id
        LEFT JOIN
    cliente cl ON pd.cliente_id = cl.id
ORDER BY
    c.id, pd.id;

-- V_CARDAPIO
CREATE VIEW V_CARDAPIO AS
SELECT
    c.id AS id_cardapio,
    u.nome AS gerente_responsavel,
    u.id AS id_gerente,

    cat.id AS id_categoria,
    cat.nome AS nome_categoria,

    cat_mae.id AS id_categoria_mae,
    cat_mae.nome AS nome_categoria_mae,

    item.id AS id_item,
    item.nome AS nome_item,
    item.ingredientes,
    item.preco,
    item.disponivel_na_cozinha
FROM
    cardapio c
        LEFT JOIN
    usuario u ON c.gerente_id = u.id -- 1. Liga Cardapio ao Gerente
        LEFT JOIN
    categoria cat ON c.id = cat.cardapio_id -- 2. Liga Cardapio a todas as Categorias
        LEFT JOIN
    categoria cat_mae ON cat.categoria_mae_id = cat_mae.id -- 3. Liga Categoria à sua Categoria Mãe
        LEFT JOIN
    item_cardapio item ON cat.id = item.categoria_id -- 4. Liga Categoria ao ItemCardapio
ORDER BY
    c.id, cat.nome, item.nome;