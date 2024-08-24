CREATE TABLE Categories (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    is_Active BOOLEAN,
    created_at BIGINT,
    updated_at BIGINT,
    description TEXT,
    parent_id BIGINT,
    status INT NOT NULL,
    CONSTRAINT fk_parent_category_id FOREIGN KEY (parent_id) REFERENCES Categories(id)
);


INSERT INTO Categories (id, name, is_Active, created_at, updated_at, description, parent_id, status) VALUES
(1, 'Men''s Wear', TRUE, 1692816000, 1692816000, 'Clothing for men', NULL, 1),
(2, 'Women''s Wear', TRUE, 1692816000, 1692816000, 'Clothing for women', NULL, 1),
(3, 'Kid''s Wear', TRUE, 1692816000, 1692816000, 'Clothing for kids', NULL, 1),
(4, 'Shirts', TRUE, 1692816100, 1692816100, 'All types of shirts', 1, 1),
(5, 'Pants', TRUE, 1692816100, 1692816100, 'All types of pants', 1, 1),
(6, 'Shoes', TRUE, 1692816200, 1692816200, 'Footwear', 1, 1),
(7, 'Dresses', TRUE, 1692816200, 1692816200, 'Dresses for women', 2, 1),
(8, 'Skirts', TRUE, 1692816200, 1692816200, 'Various skirts', 2, 1),
(9, 'T-Shirts', TRUE, 1692816300, 1692816300, 'T-shirts for kids', 3, 1),
(10, 'Shorts', TRUE, 1692816300, 1692816300, 'Shorts for kids', 3, 1),
(11, 'Accessories', TRUE, 1692816400, 1692816400, 'Accessories for men', 1, 1),
(12, 'Belts', TRUE, 1692816400, 1692816400, 'Various belts', 11, 1),
(13, 'Hats', TRUE, 1692816400, 1692816400, 'Various hats', 11, 1),
(14, 'Activewear', TRUE, 1692816500, 1692816500, 'Sportswear and activewear', 1, 1),
(15, 'Outerwear', TRUE, 1692816500, 1692816500, 'Jackets, coats, and outerwear', 1, 1),
(16, 'Sleepwear', TRUE, 1692816600, 1692816600, 'Nightwear and loungewear', 2, 1),
(17, 'Footwear', TRUE, 1692816600, 1692816600, 'Footwear for women', 2, 1),
(18, 'New Arrivals', TRUE, 1692816700, 1692816700, 'Latest collections and releases', NULL, 1),
(19, 'Sale', TRUE, 1692816700, 1692816700, 'Discounted items', NULL, 1),
(20, 'Accessories', TRUE, 1692816800, 1692816800, 'Accessories for women', 2, 1);
