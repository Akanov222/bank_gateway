-- Создаем таблицу clients
CREATE TABLE clients (
                         id varchar(255) DEFAULT gen_random_uuid() PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         tax_number VARCHAR(255) NOT NULL UNIQUE
);

-- Создаем таблицу payments
CREATE TABLE payments (
                          id varchar(255) DEFAULT gen_random_uuid() PRIMARY KEY,
                          amount DECIMAL(19,4) NOT NULL,
                          currency VARCHAR(3) NOT NULL,
                          sender_iban VARCHAR(34) NOT NULL,
                          receiver_iban VARCHAR(34) NOT NULL,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                          client_id varchar(255),
                          CONSTRAINT fk_payment_client FOREIGN KEY (client_id) REFERENCES clients(id)
);

-- Явное добавление уникального констрейнта (хотя уже есть в определении столбца)
ALTER TABLE clients ADD CONSTRAINT uk_clients_tax_number UNIQUE (tax_number);

-- Создаем индекс для tax_number
CREATE INDEX idx_clients_tax_number ON clients(tax_number);

-- Комментарии к таблицам (опционально)
COMMENT ON TABLE clients IS 'Таблица клиентов банковского шлюза';
COMMENT ON TABLE payments IS 'Таблица платежей банковского шлюза';
COMMENT ON COLUMN clients.tax_number IS 'ИНН клиента (уникальный идентификатор)';
COMMENT ON COLUMN payments.currency IS 'Валюта платежа (3-буквенный код ISO)';