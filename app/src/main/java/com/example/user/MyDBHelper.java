package com.example.user;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.Random;


import androidx.annotation.Nullable;

public class MyDBHelper extends SQLiteOpenHelper {

    public MyDBHelper(@Nullable Context context) {
        super(context, "COMP4200DB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creating UserTable
        String createUserQuery = "CREATE TABLE userTable(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "userEmail VARCHAR(100) not null unique, " +
                "userNickname VARCHAR(50) not null,  " +
                "userPassword VARCHAR(20) not null)";
        db.execSQL(createUserQuery);
        Log.d("MyDBHelper", "UserTable created");

        // Creating Transaction Table
        String createTransactionQuery = "CREATE TABLE transactionTable(_idTransaction INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "transactionTotal NUMERIC NOT NULL, " +
                "converted BOOLEAN DEFAULT 0)";
        db.execSQL(createTransactionQuery);
        Log.d("MyDBHelper", "TransactionTable created");

        // Creating Rewards Table with foreign key constraint
        String createRewardsQuery = "CREATE TABLE rewardsTable(_idReward INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "points INTEGER NOT NULL, "
                + "_idUser INTEGER, "
                + "FOREIGN KEY(_idUser) REFERENCES user(_id)"
                + ")";
        db.execSQL(createRewardsQuery);
        Log.d("MyDBHelper", "RewardsTable created");

        // Creating Coupon table
        String createCouponQuery = "CREATE TABLE couponTable(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "coupon TEXT NOT NULL"
                + ")";
        db.execSQL(createCouponQuery);
        Log.d("MyDBHelper", "Coupon Table Created");
    }


    @Override
    public void onUpgrade (SQLiteDatabase db,int oldVersion, int newVersion){
        //Drop the existing tables if they exist
        db.execSQL("DROP TABLE IF EXISTS userTable");
        db.execSQL("DROP TABLE IF EXISTS transactionTable");
        db.execSQL("DROP TABLE IF EXISTS rewardsTable");
        db.execSQL("DROP TABLE IF EXISTS couponTable");

        // Recreate the tables by calling onCreate
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the existing tables if they exist
        db.execSQL("DROP TABLE IF EXISTS userTable");
        db.execSQL("DROP TABLE IF EXISTS transactionTable");
        db.execSQL("DROP TABLE IF EXISTS rewardsTable");
        db.execSQL("DROP TABLE IF EXISTS couponTable");

        // Recreate the tables by calling onCreate
        onCreate(db);
    }



    //METHODS FOR ADDING DB RECORDS
    // Method to add a new user to the database
    public void addUser(String userEmail, String userNickname, String userPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("userEmail", userEmail);
        values.put("userNickname", userNickname);
        values.put("userPassword", userPassword);

        // Inserting Row
        long newRowId = db.insert("userTable", null, values);
    }


    // Method to add a new transaction to the database
    public void addTransaction(double transactionTotal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("transactionTotal", transactionTotal);
        values.put("converted", 0); // Setting the value to false (0)
        // Inserting Row
        long newRowId = db.insert("transactionTable", null, values);
    }

    //METHODS FOR USER TABLE
    //method for displaying userNickname on Rewards page
    public String getUserNickname(int userID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {"userNickname"};
        String selection = "_id = ?";
        String[] selectionArgs = {String.valueOf(userID)};
        Cursor cursor = db.query("userTable", projection, selection, selectionArgs, null, null, null);
        String userNickname = null;

        try {
            if (cursor != null && cursor.moveToFirst()) {
                int nicknameIndex = cursor.getColumnIndex("userNickname");
                if (nicknameIndex != -1) {
                    userNickname = cursor.getString(nicknameIndex);
                    Log.d("MyDBHelper", "User Nickname: " + userNickname);
                } else {
                    Log.e("MyDBHelper", "Column 'userNickname' not found in cursor.");
                }
            }
        } catch (Exception e) {
            Log.e("MyDBHelper", "Error fetching user nickname: " + e.getMessage());
        } finally {
            // Close the cursor
            if (cursor != null) {
                cursor.close();
            }
        }
        return userNickname;
    }

    public void updatePassword(String email, String newPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("userPassword", newPassword);

        String selection = "userEmail = ?";
        String[] selectionArgs = {email};

        int rowsAffected = db.update("userTable", values, selection, selectionArgs);
        Log.d("MyDBHelper", "Rows affected: " + rowsAffected);

        db.close(); // Close the database connection
    }

    public boolean isEmailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        boolean emailExists = false;

        try {
            // Query to check if the email exists in the user table
            String query = "SELECT COUNT(*) FROM userTable WHERE userEmail = ?";
            cursor = db.rawQuery(query, new String[]{email});

            if (cursor != null && cursor.moveToFirst()) {
                int rowCount = cursor.getInt(0);
                emailExists = rowCount > 0;
            }
        } catch (Exception e) {
            Log.e("MyDBHelper", "Error checking if email exists: " + e.getMessage());
        } finally {

            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }

        return emailExists;
    }

    //Method to update the user nickname in the Database.
    public void updateNickname(int userId, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("userNickname", name);
        String selection = "_id = ?";
        String[] selectionArgs = {String.valueOf(userId)};
        int rowsAffected = db.update("userTable", values, selection, selectionArgs);
        Log.d("MyDBHelper", "Rows affected: " + rowsAffected);
    }


    //Method to update the user Email in the Database
    public void updateEmail(int userId, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("userEmail", email);
        String selection = "_id = ?";
        String[] selectionArgs = {String.valueOf(userId)};
        int rowsAffected = db.update("userTable", values, selection, selectionArgs);
        Log.d("MyDBHelper", "Rows affected: " + rowsAffected);
    }


    //METHODS FOR REWARDS TABLE
    //method for updating user points in Rewards table
    public void updateRewardsPoints(int userId, int points) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("points", points);
        String selection = " _idUser = ?";
        String[] selectionArgs = {String.valueOf(userId)};
        int rowsAffected = db.update("rewardsTable", values, selection, selectionArgs);
        Log.d("MyDBHelper", "Rows affected: " + rowsAffected);
    }

    //method for deleting record in Rewards table where _idRewards = value
    public void deleteReward(int rewardId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "_idReward = ?";
        String[] selectionArgs = {String.valueOf(rewardId)};
        int rowsDeleted = db.delete("rewardsTable", selection, selectionArgs);
        Log.d("MyDBHelper", "Rows deleted: " + rowsDeleted);
    }


    // Method to get points value for a specific user ID
    public int getPointsValue(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        int pointsValue = 0;
        Cursor cursor = null;

        try {
            // Query to retrieve points value for the given user ID
            String query = "SELECT points FROM rewardsTable WHERE _idUSER = ?";
            cursor = db.rawQuery(query, new String[]{String.valueOf(userId)});

            // Check if the cursor has data
            if (cursor != null && cursor.moveToFirst()) {
                int pointsIndex = cursor.getColumnIndex("points");
                if (pointsIndex != -1) {
                    pointsValue = cursor.getInt(pointsIndex);
                    Log.d("MyDBHelper", "Points Value: " + pointsValue);
                } else {
                    Log.e("MyDBHelper", "Column 'points' not found in cursor.");
                }
            }
        } catch (Exception e) {
            Log.e("MyDBHelper", "Error fetching points value: " + e.getMessage());
        } finally {
            // Close the cursor
            if (cursor != null) {
                cursor.close();
            }
        }
        return pointsValue;
    }
    public Double[] getTable(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {"_idTransaction", "transactionTotal", "converted"};
        String selection = "_idTransaction = ?";
        String[] selectionArgs = {String.valueOf(id)};
        Cursor cursor = db.query("transactionTable", projection, selection, selectionArgs, null, null, null);
        Double tID=0.0;
        Double amount=0.0;
        Double pts=0.0;
        Double[] transaction = {};
        try {
            if (cursor != null && cursor.moveToFirst()) {
                int tIDIndex = cursor.getColumnIndex("_idTransaction");
                if (tIDIndex != -1) {
                    tID = cursor.getDouble(tIDIndex);
                    Log.d("MyDBHelper", "Transaction ID: " + tID);
                } else {
                    Log.e("MyDBHelper", "Column 'tID' not found in cursor.");
                }

                int amountIndex = cursor.getColumnIndex("transactionTotal");
                if (amountIndex != -1) {
                    amount = cursor.getDouble(amountIndex);
                    Log.d("MyDBHelper", "TransactionTotal: " + amount);
                } else {
                    Log.e("MyDBHelper", "Column 'transactionTotal' not found in cursor.");
                }
            }
            int ptsIndex = cursor.getColumnIndex("converted");
            if (ptsIndex != -1) {
                pts = cursor.getDouble(ptsIndex);
                Log.d("MyDBHelper", "pts: " + tID);
            } else {
                Log.e("MyDBHelper", "Column 'pts' not found in cursor.");
            }
        } catch (Exception e) {
            Log.e("MyDBHelper", "Error fetching user info: " + e.getMessage());
        } finally {
            // Close the cursor
            if (cursor != null) {
                cursor.close();
            }
        }
        transaction[0] = tID;
        transaction[1] = amount;
        transaction[2] = pts;

        return transaction;
    }

    // Method to add a new reward to the database if _idUser does not already exist
    public void addReward(int points, int userId) {
        if (!isUserIdExists(userId)) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("points", points);
            values.put("_idUser", userId);
            // Inserting Row
            long newRowId = db.insert("rewardsTable", null, values);
        } else {
            Log.d("MyDBHelper", "User ID already exists in rewards table.");
        }
    }

    // Method to check if _idUser already exists in the rewards table
    private boolean isUserIdExists(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        boolean exists = false;
        try {
            String query = "SELECT _idUser FROM rewardsTable WHERE _idUser = ?";
            cursor = db.rawQuery(query, new String[]{String.valueOf(userId)});
            exists = cursor != null && cursor.getCount() > 0;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return exists;
    }


    //METHODS FOR TRANSACTION TABLE
    //Get the transaction id and return the amount
    public double getAmountFromTransactionID(int transactionID) {
        SQLiteDatabase db = this.getReadableDatabase();
        double amount = 0;
        Cursor cursor = null;

        try {
            // Query to retrieve transaction total value for the given user ID
            String query = "SELECT transactionTotal FROM transactionTable WHERE _idTransaction = ?";
            cursor = db.rawQuery(query, new String[]{String.valueOf(transactionID)});

            // Check if the cursor has data
            if (cursor != null && cursor.moveToFirst()) {
                int amountIndex = cursor.getColumnIndex("transactionTotal");
                if (amountIndex != -1) {
                    amount = cursor.getDouble(amountIndex);
                    Log.d("MyDBHelper", "Transaction Amount: " + amount);
                } else {
                    Log.e("MyDBHelper", "Column 'transactionTotal' not found in cursor.");
                }
            }
        } catch (Exception e) {
            Log.e("MyDBHelper", "Error fetching transaction amount: " + e.getMessage());
        } finally {
            // Close the cursor
            if (cursor != null) {
                cursor.close();
            }
        }
        return amount;
    }
    //A method that checks if the amount has been converted to points or not:
    public boolean isPointsConverted(int transactionID) {
        SQLiteDatabase db = this.getReadableDatabase();
        boolean isConverted = false;
        Cursor cursor = null;

        try {
            // Query to retrieve points value for the given user ID
            String query = "SELECT converted FROM transactionTable WHERE _idTransaction = ?";
            cursor = db.rawQuery(query, new String[]{String.valueOf(transactionID)});

            // Check if the cursor has data
            if (cursor != null && cursor.moveToFirst()) {
                //goes to the column called converted in the transaction table:
                int convertedIndex = cursor.getColumnIndex("converted");
                if (convertedIndex != -1) {
                    //points have been converted:
                    isConverted = cursor.getInt(convertedIndex) == 1;
                    Log.d("MyDBHelper", "Points Converted: " + isConverted);
                } else {
                    Log.e("MyDBHelper", "Column 'converted' not found in cursor.");
                }
            }
        } catch (Exception e) {
            Log.e("MyDBHelper", "Error fetching transaction amount: " + e.getMessage());
        } finally {
            // Close the cursor
            if (cursor != null) {
                cursor.close();
            }
        }
        return isConverted;
    }

    //Method to make sure the users Email and Password matchs the Email and Password in the Database.
    public Boolean checkUserPass(String email, String pass) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = null;
        try {
            cursor = MyDB.rawQuery("Select * from userTable where userEmail = ? and userPassword = ?", new String[]{email, pass});
        }catch (Exception e){

        }

        if(cursor.getCount()>0) {
            return true;
        }
        else
            return false;
    }
    //Method to retrieve the ser ID using the inputed email.
    public int getID(String email){
        int id = -1;
        Cursor cursor = null;
        try {
            SQLiteDatabase MyDB = this.getReadableDatabase();
            cursor = MyDB.rawQuery("Select _id from userTable where userEmail = ?", new String[]{email});
            if (cursor != null && cursor.moveToFirst()) {
                int idIndex = cursor.getColumnIndex("_id");
                id = cursor.getInt(idIndex);
                if (idIndex != -1) {
                    id = cursor.getInt(idIndex);
                    Log.d("MyDBHelper", "ID received: " + id);
                } else {
                    Log.e("MyDBHelper", "Column '_id' not found in cursor.");
                }
            }
        }
        catch(Exception e){
            Log.e("MyDBHelper", "Error fetching id: " + e.getMessage());
        } finally {
            if(cursor!= null)
                cursor.close();
        }
        return id;
    }

    //updates the convert column when the points have been converted
    public void updateConvertColumn (int transactionID){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //update the converted column to 1 - indicating that the transaction amount has been converted
        values.put("converted",1);
        String selection = "_idTransaction = ?";
        String [] selectionArgs = {String.valueOf(transactionID)};
        int rowsAffected = db.update("transactionTable",values,selection, selectionArgs);
        Log.d ("MyDBHelper", "Rows affected: " + rowsAffected);

    }


    //COUPON TABLE QUERIES
    public String addCoupon() {
        String couponCode = generateRandomCoupon();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("coupon", couponCode);
        long newRowId = db.insert("couponTable", null, values);
        Log.d("MyDBHelper", "New coupon added with code: " + couponCode);
        db.close();
        return couponCode;
    }


    private String generateRandomCoupon() {
        // Generate a random 6-digit number
        Random random = new Random();
        int randomNumber = random.nextInt(900000) + 100000;
        return String.valueOf(randomNumber);
    }

}