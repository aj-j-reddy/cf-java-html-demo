package com.example.demo.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Books;
import com.example.demo.model.Database;
@Component
public class NativeSQL {

    Logger logger = LoggerFactory.getLogger(NativeSQL.class);
    private Connection connection = null;
    @Autowired Database db;

    public void createConnection() {

        long startTime = System.currentTimeMillis();


        logger.info("Java version: " + com.sap.db.jdbc.Driver.getJavaVersion());
        logger.info("Minimum supported Java version and SAP driver version number: "
                + com.sap.db.jdbc.Driver.getVersionInfo());

        try {
            connection = DriverManager.getConnection(db.getUrl(), db.getUsername(), db.getPassword());

            if (connection != null) {

                logger.info("Connection to HANA successful!");
            }

            long endTime = System.currentTimeMillis();
            long executeTime = endTime - startTime;

            logger.info("HANA Connection Time:"  + executeTime);

        } catch (SQLException e) {
            logger.error("Connection Failed:");
            logger.error(e.toString());
            return;
        }
    }
    /*
    public void insertJob(Books job) {
        if (connection != null) {
            try {
                long startTime = System.currentTimeMillis();

                PreparedStatement pstmt = connection.prepareStatement(
                        "INSERT INTO \"ECM_JOB_EXECUTION_STATUS\" (\"JOB_ID\", \"JOB_NAME\", \"RESULT\", \"START_TIME\", \"STATUS\") VALUES (?, ?, ?, ?, ?)");
                pstmt.setNString(1, job.getJobId());
                pstmt.setNString(2, job.getJobName());
                pstmt.setNString(3, job.getResult());
                pstmt.setLong(4, job.getStartTime());
                pstmt.setNString(5, job.getStatus());

                pstmt.executeUpdate();

                long endTime = System.currentTimeMillis();
                long executeTime = endTime - startTime;

                logger.info("Insert Job:" + job.getJobId() + ":" + executeTime);

            } catch (SQLException e) {
                logger.error("Insert failed!");
                logger.error(e.toString());
            }
        }
    }

    public void updateJob(JobExecutionStatus job) {
        if (connection != null) {
            try {
                long startTime = System.currentTimeMillis();

                PreparedStatement pstmt = connection.prepareStatement(
                        "UPDATE \"ECM_JOB_EXECUTION_STATUS\" SET \"RESULT\" = ?, \"STATUS\" = ? WHERE (\"JOB_ID\" = ?)");
                pstmt.setNString(1, job.getResult());
                pstmt.setNString(2, job.getStatus());
                pstmt.setNString(3, job.getJobId());
                pstmt.executeUpdate();

                long endTime = System.currentTimeMillis();
                long executeTime = endTime - startTime;

                logger.info("Update Job:" + job.getJobId() + ":" + executeTime);

            } catch (SQLException e) {
                logger.error("Update failed!");
                logger.error(e.toString());
            }
        }
    }
 */
    public List<Books> getBooks() {
        List<Books> booksList = new ArrayList<Books>();
        if (connection != null) {
            try {
                long startTime = System.currentTimeMillis();

                PreparedStatement pstmt = connection.prepareStatement(
                        "SELECT * FROM \"BOOKS\";");

                ResultSet rs = pstmt.executeQuery();
                Books book = null;
                while(rs.next()){
                    book = new Books();
                    book.setBookId(rs.getString("ID"));
                    book.setBookName(rs.getString("NAME"));
                    book.setPublishedOn(rs.getString("PUBLISHED_ON"));
                    book.setAuthor(rs.getString("AUTHOR"));
                    book.setCount(rs.getInt("COUNT"));
                    logger.info("Book read");
                    booksList.add(book);
                }

                logger.info("Books read:" + booksList.size());

            } catch (SQLException e) {
                logger.error("Query failed!");
                logger.error(e.toString());
            }
        }
        return booksList;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

}