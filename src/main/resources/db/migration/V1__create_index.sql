CREATE OR REPLACE FUNCTION immutable_to_tsvector(text) RETURNS tsvector AS
$$
BEGIN
    RETURN to_tsvector($1);
END;
$$ LANGUAGE plpgsql IMMUTABLE;

-- Create the index using the immutable function
CREATE INDEX idx_tsvector_title ON issues USING GIN (immutable_to_tsvector(title));