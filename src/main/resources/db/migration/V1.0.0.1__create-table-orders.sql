-- Создание таблицы ингредиентов
CREATE TABLE ingredients (
                             id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                             name VARCHAR(255) NOT NULL
);

-- Создание таблицы рецептов
CREATE TABLE recipe (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        description TEXT
);

-- Создание таблицы с ингредиентами для рецептов
CREATE TABLE recipe_ingredients (
                                    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                                    recipe_id INT REFERENCES recipe(id),
                                    ingredients_id INT REFERENCES ingredients(id),
                                    amount INT NOT NULL  -- Количество ингредиента
);

-- Создание таблицы для кофемашин
CREATE TABLE coffee_machine (
                                id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY
);

-- Таблица для связывания кофемашин с рецептами
CREATE TABLE coffee_machine_recipe (
                                       id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                                       coffee_machine_id INT REFERENCES coffee_machine(id),
                                       recipe_id INT REFERENCES recipe(id)
);

-- Таблица для управления запасами ингредиентов в кофемашине
CREATE TABLE coffee_machine_resources (
                                          id SERIAL PRIMARY KEY,
                                          machine_id INT REFERENCES coffee_machine(id),
                                          ingredient_id INT REFERENCES ingredients(id),
                                          amount INT NOT NULL  -- Количество ингредиента в кофемашине
);

-- Таблица для отслеживания заказов
CREATE TABLE orders (
                        id SERIAL PRIMARY KEY,
                        recipe_id INT REFERENCES recipe(id),
                        coffee_machine_id INT REFERENCES coffee_machine(id),
                        order_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);