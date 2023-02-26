package org.popcraft.bolt.data.sql;

public enum Statements {
    CREATE_TABLE_BLOCKS(
            "CREATE TABLE IF NOT EXISTS %sblocks (id varchar(36) PRIMARY KEY, owner varchar(36), type varchar(128), created integer, accessed integer, access text, world varchar(128), x integer, y integer, z integer, block varchar(128));",
            "CREATE TABLE IF NOT EXISTS %sblocks (id varchar(36) PRIMARY KEY, owner varchar(36), type varchar(128), created bigint, accessed bigint, access text, world varchar(128), x integer, y integer, z integer, block varchar(128));"
    ),
    CREATE_TABLE_ENTITIES(
            "CREATE TABLE IF NOT EXISTS %sentities (id varchar(36) PRIMARY KEY, owner varchar(36), type varchar(128), created integer, accessed integer, access text, entity varchar(128));",
            "CREATE TABLE IF NOT EXISTS %sentities (id varchar(36) PRIMARY KEY, owner varchar(36), type varchar(128), created bigint, accessed bigint, access text, entity varchar(128));"
    ),
    CREATE_INDEX_BLOCK_OWNER(
            "CREATE INDEX IF NOT EXISTS block_owner ON %sblocks(owner);"
    ),
    CREATE_INDEX_BLOCK_LOCATION(
            "CREATE UNIQUE INDEX IF NOT EXISTS block_location ON %sblocks(world, x, y, z);"
    ),
    CREATE_INDEX_ENTITY_OWNER(
            "CREATE INDEX IF NOT EXISTS entity_owner ON %sentities(owner);"
    ),
    SELECT_BLOCK_BY_LOCATION(
            "SELECT * FROM %sblocks WHERE world = ? AND x = ? AND y = ? AND z = ?;"
    ),
    SELECT_ALL_BLOCKS(
            "SELECT * FROM %sblocks;"
    ),
    REPLACE_BLOCK(
            "REPLACE INTO %sblocks VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);"
    ),
    DELETE_BLOCK(
            "DELETE FROM %sblocks WHERE world = ? AND x = ? AND y = ? AND z = ?;"
    ),
    SELECT_ENTITY_BY_UUID(
            "SELECT * FROM %sentities WHERE id = ?;"
    ),
    SELECT_ALL_ENTITIES(
            "SELECT * FROM %sentities;"
    ),
    REPLACE_ENTITY(
            "REPLACE INTO %sentities VALUES (?, ?, ?, ?, ?, ?, ?);"
    ),
    DELETE_ENTITY(
            "DELETE FROM %sentities WHERE id = ?;"
    ),
    LWC_SELECT_ALL_BLOCK_IDS(
            "SELECT * FROM %sblocks;"
    ),
    LWC_SELECT_ALL_PROTECTIONS(
            "SELECT * FROM %sprotections;"
    ),
    LWC_INSERT_BLOCK_ID(
            "INSERT INTO %sblocks VALUES (?, ?);"
    ),
    LWC_INSERT_OR_IGNORE_PROTECTION(
            "INSERT OR IGNORE INTO %sprotections (owner, type, x, y, z, data, blockId, world, password, date, last_accessed, rights) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);",
            "INSERT IGNORE INTO %sprotections (owner, type, x, y, z, data, blockId, world, password, date, last_accessed, rights) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);"
    );

    private final String sqlite;
    private final String mysql;

    Statements(final String sqlite, final String mysql) {
        this.sqlite = sqlite;
        this.mysql = mysql;
    }

    Statements(final String sqlite) {
        this.sqlite = sqlite;
        this.mysql = sqlite;
    }

    public String get(final String type) {
        return "sqlite".equals(type) ? sqlite : mysql;
    }
}