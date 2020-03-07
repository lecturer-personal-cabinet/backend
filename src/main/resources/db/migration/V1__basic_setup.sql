CREATE DOMAIN entity_key VARCHAR(50);

create function generate_entity_key(prefix varchar(20)) returns entity_key
as $$
begin
    RETURN concat(prefix, UPPER(SUBSTRING(MD5(''||NOW()::TEXT||RANDOM()::TEXT) FOR 20)));
end;
$$ LANGUAGE PLPGSQL;