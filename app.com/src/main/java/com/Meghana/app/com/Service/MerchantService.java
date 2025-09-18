package com.Meghana.app.com.Service;

import com.Meghana.app.com.Dto.MerchantRequestdto;
import com.Meghana.app.com.Dto.MerchantResDto;
import com.Meghana.app.com.Dto.MerchantResponseDto;
import com.Meghana.app.com.Entity.Merchant;
import com.Meghana.app.com.Exception.ResourceNotFoundEx;
import com.Meghana.app.com.Repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MerchantService {
    @Autowired
    private MerchantRepository merchantRepo;

    public MerchantResDto mapToDto(Merchant merchant){
        MerchantResDto resDto=new MerchantResDto();
        resDto.setAddress(merchant.getAddress());
        resDto.setName(merchant.getName());
        resDto.setId(merchant.getId());
        resDto.setCompanyName(merchant.getCompanyName());

        return resDto;
    }

    public MerchantResponseDto createMerchant(MerchantRequestdto merchantReq){
        Optional<Merchant> existingMerchant = merchantRepo.findByCompanyName(merchantReq.getCompanyName());

        if (existingMerchant.isPresent()){
            throw new RuntimeException("This Company is already exists.");
        }

        Merchant merchant = new Merchant();
        merchant.setAddress(merchantReq.getAddress());
        merchant.setName(merchantReq.getName());
        merchant.setCompanyName(merchantReq.getCompanyName());
        merchant.setPhoneNumber(merchantReq.getPhoneNumber());
        merchant.setAddress(merchantReq.getAddress());

        Merchant savedMerchant =  merchantRepo.save(merchant);
        return new MerchantResponseDto("Merchant Successfully  created with id "+savedMerchant.getId());
    }

    public List<MerchantResDto>  getAllMerchants(){
        return merchantRepo.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    public MerchantResDto getMerchantById(Long id){
        Merchant merchant= merchantRepo.findById(id).orElseThrow(()-> new ResourceNotFoundEx("Merchant with this id "+id+"does not exists"));

        return mapToDto(merchant);
    }

    public MerchantResDto updateMerchant(Long id,MerchantRequestdto request){
        Merchant existingMerchant = merchantRepo.findById(id).orElseThrow(()->new ResourceNotFoundEx("Merchant with id "+id+ " does not exists"));
        existingMerchant.setName(request.getName());
        existingMerchant.setAddress(request.getAddress());
        existingMerchant.setPhoneNumber(request.getPhoneNumber());
        existingMerchant.setCompanyName(request.getCompanyName());
        Merchant merchant= merchantRepo.save(existingMerchant);
        return mapToDto(merchant);
    }

    public void deleteMerchant(Long id){
        Merchant existingMerchant = merchantRepo.findById(id).orElseThrow(()->new ResourceNotFoundEx("Merchant with id "+id+ " does not exists"));
        merchantRepo.delete(existingMerchant);
    }
}
