package com.extremecommandos.pocket_zalcoatl.snakeGame;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kevin on 15/05/2016.
 */
 public class Score implements Parcelable {
    private int Data;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(Data);
    }

    public static final Parcelable.Creator<Score> CREATOR
            = new Parcelable.Creator<Score>() {
        public Score createFromParcel(Parcel in) {
            return new Score(in);
        }

        public Score[] newArray(int size) {
            return new Score[size];
        }
    };

    private Score(Parcel in) {
        Data = in.readInt();
    }

    public Score() {
    }

    public int getData() {
        return Data;
    }
    public void setData(int Data){
        this.Data = Data;
    }
}
