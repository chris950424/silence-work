package com.silence.stock.infrastructure.Imports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCImpl implements UserC{
    @Autowired
    private UserD userD;
}
