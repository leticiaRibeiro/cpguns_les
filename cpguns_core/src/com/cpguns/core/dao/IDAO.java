
package com.cpguns.core.dao;

import com.cpguns.core.model.DomainEntity;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Leticia
 */
public interface IDAO {
    
    public void create(DomainEntity entidade) throws SQLException;
    public List<DomainEntity> read(DomainEntity entidade) throws SQLException;
    public void update(DomainEntity entidade) throws SQLException;
    public void delete(DomainEntity entidade) throws SQLException;
    
    
}
