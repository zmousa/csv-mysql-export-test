PM Project - ETL Tests
======================
Scala project for **unit testing** export process from database into `CSV` files, including:
 * Testing database connection
 * Testing `CSV` file existence in folder
 * Testing number of columns and number of row matched to the corresponded table/query
 * Testing data format (date/nullable)
 * Testing content (by hashing each row content)

Properties
----------
 * JDBC connection with MYSQL database
 * CSV reader from folder of files



