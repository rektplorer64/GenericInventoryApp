package tanawinwichitcom.android.inventoryapp.roomdatabase.Entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;
import java.util.List;

import tanawinwichitcom.android.inventoryapp.roomdatabase.DateConverter;

@Entity(tableName = "reviews")
public class Review{

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    protected int id;

    @TypeConverters(DateConverter.class)
    @ColumnInfo(name = "timeStamp")
    private Date timeStamp;

    @ColumnInfo(name = "userId")
    private int userId;

    @ColumnInfo(name = "itemId")
    private int itemId;

    @ColumnInfo(name = "commentString")
    private String comment;

    @ColumnInfo(name = "ratingValue")
    private double rating;

    public Review(Date timeStamp, int userId, int itemId, String comment, double rating){
        this.timeStamp = timeStamp;
        this.userId = userId;
        this.itemId = itemId;
        this.comment = comment;
        this.rating = rating;
    }

    public static double calculateAverage(List<Review> reviewArrayList){

        // If there is no review for the item
        if(reviewArrayList == null || reviewArrayList.isEmpty()){
            return 0.0;     /* The average score is always zero */
        }

        double summation = 0.0;
        for(Review review : reviewArrayList){
            summation += review.rating;
        }

        double result = summation / reviewArrayList.size();
        if(Double.isNaN(result)){
            return 0.0;
        }else{
            return result;
        }
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public Date getTimeStamp(){
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp){
        this.timeStamp = timeStamp;
    }

    public int getUserId(){
        return userId;
    }

    public void setUserId(int userId){
        this.userId = userId;
    }

    public String getComment(){
        return comment;
    }

    public void setComment(String comment){
        this.comment = comment;
    }

    public double getRating(){
        return rating;
    }

    public void setRating(double rating){
        this.rating = rating;
    }

    public int getItemId(){
        return itemId;
    }

    public void setItemId(int itemId){
        this.itemId = itemId;
    }
}