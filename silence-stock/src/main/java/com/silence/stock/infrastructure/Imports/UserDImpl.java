package com.silence.stock.infrastructure.Imports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDImpl implements UserD {

    @Autowired
    private UserC userC;
}
