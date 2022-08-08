using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Text;

namespace CommonLibrary.Utils
{
    public class DatabaseUtils
    {
        //1. Create a connection with Database
        //2. Prepare an SQL Query
        //3. Execute the query -- SELECT (ExecuteReader()) or Non-Select query (ExecuteNonQuery())
        //4. Process the data
        //5. Close the connection

        private SqlConnection connection;

        public void CreateConnection(string dataSource, string userId,string password, string dbName)
        {
            SqlConnectionStringBuilder builder = new SqlConnectionStringBuilder
            {
                DataSource = dataSource,
                UserID = userId,
                Password = password,
                InitialCatalog = dbName
            };

            connection = new SqlConnection(builder.ConnectionString);

            connection.Open();
        }

        public void CloseConnection() => connection.Close();

        public DataTable ExecuteSelectQuery(string sqlQuery)
        {
            SqlCommand command = new SqlCommand(sqlQuery, connection);
            SqlDataReader reader = command.ExecuteReader();

            DataTable dataTable = new DataTable();

            dataTable.Load(reader);

            return dataTable;

        }

        public int ExecuteNonSelectQuery(string sqlQuery)
        {
            SqlCommand command = new SqlCommand(sqlQuery, connection);

            int rowAffected = command.ExecuteNonQuery();

            return rowAffected;
        }

    }
}
