package com.coliseum.model.entities;

import java.io.Serializable;

public interface Identified<PK extends Serializable> {

    PK getId();

}