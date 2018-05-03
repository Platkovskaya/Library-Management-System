package com.epam.library.dao;

import com.epam.library.domain.AccountingData;

import java.util.List;

/**
 * Created by Irina on 22.04.2018.
 */
public interface AccountingDAO<T extends AccountingData> extends BaseDAO {

    List<T> getRentedInformation();

}
