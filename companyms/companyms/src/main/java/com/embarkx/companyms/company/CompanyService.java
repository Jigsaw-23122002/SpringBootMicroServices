package com.embarkx.companyms.company;

import java.util.List;


public interface CompanyService {
    List<Company> getAllCompanies();
    void createCompany(Company company);
    boolean updateCompany(Company company, Long id);
    boolean deleteCompany(Long id);
    Company getCompanyById(Long id);
}
