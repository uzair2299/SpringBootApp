--CREATE PROCEDURE GetEmployeesByDepartment11
--AS
--BEGIN
--SELECT TOP (1000) [id]
--      ,[created_at]
--      ,[description]
--      ,[role_name]
--      ,[updated_at]
--  FROM [PointOfSale].[dbo].[role]
--END
--
--


IF OBJECT_ID('GetEmployeesByDepartment22', 'P') IS NOT NULL
BEGIN
    EXEC('DROP PROCEDURE GetEmployeesByDepartment22');
END


EXEC('
CREATE PROCEDURE GetEmployeesByDepartment22
AS
BEGIN
    SELECT TOP (1000) [id]
          ,[created_at]
          ,[description]
          ,[role_name]
          ,[updated_at]
      FROM [PointOfSale].[dbo].[role]
END
');
