package fr.uge.lootin.back.models;

import javax.persistence.*;

@Entity(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Lob
    @Column(name = "image")
    private byte[] image;

    public Image() {}

    public Image(String name, byte[] image){
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
