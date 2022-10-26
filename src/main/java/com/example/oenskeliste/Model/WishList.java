package com.example.oenskeliste.Model;

public class WishList implements Comparable<WishList>{

    private int id;
    private String name;
    private String description;
    private int ownerId;
    private boolean isOwner;


    public WishList(String name, String description, int ownerId) {
        this.name = name;
        this.description = description;
        this.ownerId = ownerId;
    }

    public WishList(int id, String name, String description, int ownerId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ownerId = ownerId;
    }

    public WishList(int id, String name, String description, int ownerId, boolean isOwner) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ownerId = ownerId;
        this.isOwner = isOwner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public boolean isOwner() {
        return isOwner;
    }

    public void setOwner(boolean owner) {
        isOwner = owner;
    }

    @Override
    public String toString() {
        return "WishList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", ownerId=" + ownerId +
                ", isOwner=" + isOwner +
                '}';
    }

    @Override
    public int compareTo(WishList o) {
        return this.name.compareTo(o.name);
    }
}
