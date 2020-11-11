package pq.jdev.b001.bookstore.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import pq.jdev.b001.bookstore.books.model.Book;
import pq.jdev.b001.bookstore.books.model.BookInfo;
import pq.jdev.b001.bookstore.books.service.BookService;
import pq.jdev.b001.bookstore.cart.dto.CustomerDTO;
import pq.jdev.b001.bookstore.cart.model.CartInfo;
import pq.jdev.b001.bookstore.cart.model.CustomerInfo;
import pq.jdev.b001.bookstore.cart.service.CartService;
import pq.jdev.b001.bookstore.cart.utils.Utils;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;


@Controller
public class CartController {
    
   @Autowired
   private CartService cartService;

   @Autowired
   private BookService bookService;

   @RequestMapping({ "/buyBook" })
   public String listProductHandler(HttpServletRequest request, Model model, Authentication authentication, ModelMap map, //
         @RequestParam(value = "bookId", defaultValue = "") Long bookId) {

      if (authentication != null) {
         Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
         List<String> roles = new ArrayList<String>();
         for (GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
         }
         if (isUser(roles)) {
            map.addAttribute("header", "header_user");
            map.addAttribute("footer", "footer_user");
         } else if (isAdmin(roles)) {
            map.addAttribute("header", "header_admin");
            map.addAttribute("footer", "footer_admin");
         }
      } else {
         map.addAttribute("header", "header_login");
         map.addAttribute("footer", "footer_login");
      }
 
      Book book = null;
      if (bookId != null) {
        book = bookService.findBookByID(bookId);
      }
      if (book != null) {
 
         //
         CartInfo cartInfo = Utils.getCartInSession(request);
 
         BookInfo bookInfo = new BookInfo(book);
 
         cartInfo.addBook(bookInfo, 1);
      }
 
      return "redirect:/shoppingCart";
   }

   @RequestMapping({ "/shoppingCartRemoveBook" })
   public String removeProductHandler(HttpServletRequest request, Model model, //
         @RequestParam(value = "bookId", defaultValue = "") Long bookId) {
      Book book = null;
      if (bookId != null) {
         book = bookService.findBookByID(bookId);
      }
      if (book != null) {
 
         CartInfo cartInfo = Utils.getCartInSession(request);
 
         BookInfo bookInfo = new BookInfo(book);
 
         cartInfo.removeProduct(bookInfo);
 
      }
 
      return "redirect:/shoppingCart";
   }

   // POST: Update quantity for product in cart
   @RequestMapping(value = { "/shoppingCart" }, method = RequestMethod.POST)
   public String shoppingCartUpdateQty(HttpServletRequest request, Authentication authentication, ModelMap map,//
           Model model, //
           @ModelAttribute("cartForm") CartInfo cartForm) {

      if (authentication != null) {
         Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
         List<String> roles = new ArrayList<String>();
         for (GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
         }
         if (isUser(roles)) {
            map.addAttribute("header", "header_user");
            map.addAttribute("footer", "footer_user");
         } else if (isAdmin(roles)) {
            map.addAttribute("header", "header_admin");
            map.addAttribute("footer", "footer_admin");
         }
      } else {
         map.addAttribute("header", "header_login");
         map.addAttribute("footer", "footer_login");
      }
   
      CartInfo cartInfo = Utils.getCartInSession(request);
      cartInfo.updateQuantity(cartForm);
   
      return "redirect:/shoppingCart";
   }


    // GET: Show cart.
    @RequestMapping(value = { "/shoppingCart" }, method = RequestMethod.GET)
    public String shoppingCartHandler(HttpServletRequest request, Model model, Authentication authentication, ModelMap map) {

       if (authentication != null) {
         Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
         List<String> roles = new ArrayList<String>();
         for (GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
         }
         if (isUser(roles)) {
            map.addAttribute("header", "header_user");
            map.addAttribute("footer", "footer_user");
         } else if (isAdmin(roles)) {
            map.addAttribute("header", "header_admin");
            map.addAttribute("footer", "footer_admin");
         }
      } else {
         map.addAttribute("header", "header_login");
         map.addAttribute("footer", "footer_login");
      }
       CartInfo myCart = Utils.getCartInSession(request);
  
       model.addAttribute("cartForm", myCart);
       return "shoppingCart";
    }
  
    // GET: Enter customer information.
    @RequestMapping(value = { "/shoppingCartCustomer" }, method = RequestMethod.GET)
    public String shoppingCartCustomerForm(HttpServletRequest request, Model model, Authentication authentication, ModelMap map) {

      if (authentication != null) {
         Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
         List<String> roles = new ArrayList<String>();
         for (GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
         }
         if (isUser(roles)) {
            map.addAttribute("header", "header_user");
            map.addAttribute("footer", "footer_user");
         } else if (isAdmin(roles)) {
            map.addAttribute("header", "header_admin");
            map.addAttribute("footer", "footer_admin");
         }
      } else {
         map.addAttribute("header", "header_login");
         map.addAttribute("footer", "footer_login");
      }
  
       CartInfo cartInfo = Utils.getCartInSession(request);
  
       if (cartInfo.isEmpty()) {
  
          return "redirect:/shoppingCart";
       }
       CustomerInfo customerInfo = cartInfo.getCustomerInfo();
  
       CustomerDTO customerDTO = new CustomerDTO(customerInfo);
  
       model.addAttribute("customerDTO", customerDTO);
  
       return "shoppingCartCustomer";
    }

    // POST: Save customer information.
   @RequestMapping(value = { "/shoppingCartCustomer" }, method = RequestMethod.POST)
   public String shoppingCartCustomerSave(HttpServletRequest request, Authentication authentication, ModelMap map, //
         Model model, //
         @ModelAttribute("customerDTO") @Validated CustomerDTO customerForm, //
         BindingResult result, //
         final RedirectAttributes redirectAttributes) {

      if (authentication != null) {
         Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
         List<String> roles = new ArrayList<String>();
         for (GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
         }
         if (isUser(roles)) {
            map.addAttribute("header", "header_user");
            map.addAttribute("footer", "footer_user");
         } else if (isAdmin(roles)) {
            map.addAttribute("header", "header_admin");
            map.addAttribute("footer", "footer_admin");
         }
      } else {
         map.addAttribute("header", "header_login");
         map.addAttribute("footer", "footer_login");
      }
 
      if (result.hasErrors()) {
         customerForm.setValid(false);
         // Forward to reenter customer info.
         return "shoppingCartCustomer";
      }
 
      customerForm.setValid(true);
      CartInfo cartInfo = Utils.getCartInSession(request);
      CustomerInfo customerInfo = new CustomerInfo(customerForm);
      cartInfo.setCustomerInfo(customerInfo);
 
      return "redirect:/shoppingCartConfirmation";
   }
 
   // GET: Show information to confirm.
   @RequestMapping(value = { "/shoppingCartConfirmation" }, method = RequestMethod.GET)
   public String shoppingCartConfirmationReview(HttpServletRequest request, Model model, Authentication authentication, ModelMap map) {

      if (authentication != null) {
         Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
         List<String> roles = new ArrayList<String>();
         for (GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
         }
         if (isUser(roles)) {
            map.addAttribute("header", "header_user");
            map.addAttribute("footer", "footer_user");
         } else if (isAdmin(roles)) {
            map.addAttribute("header", "header_admin");
            map.addAttribute("footer", "footer_admin");
         }
      } else {
         map.addAttribute("header", "header_login");
         map.addAttribute("footer", "footer_login");
      }
      CartInfo cartInfo = Utils.getCartInSession(request);
 
      if (cartInfo == null || cartInfo.isEmpty()) {
 
         return "redirect:/shoppingCart";
      } else if (!cartInfo.isValidCustomer()) {
 
         return "redirect:/shoppingCartCustomer";
      }
      model.addAttribute("myCart", cartInfo);
 
      return "shoppingCartConfirmation";
   }
 
   // POST: Submit Cart (Save)
   @RequestMapping(value = { "/shoppingCartConfirmation" }, method = RequestMethod.POST)
 
   public String shoppingCartConfirmationSave(HttpServletRequest request, Model model, Authentication authentication, ModelMap map) {
      if (authentication != null) {
         Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
         List<String> roles = new ArrayList<String>();
         for (GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
         }
         if (isUser(roles)) {
            map.addAttribute("header", "header_user");
            map.addAttribute("footer", "footer_user");
         } else if (isAdmin(roles)) {
            map.addAttribute("header", "header_admin");
            map.addAttribute("footer", "footer_admin");
         }
      } else {
         map.addAttribute("header", "header_login");
         map.addAttribute("footer", "footer_login");
      }

      CartInfo cartInfo = Utils.getCartInSession(request);
 
      if (cartInfo.isEmpty()) {
 
         return "redirect:/shoppingCart";
      } else if (!cartInfo.isValidCustomer()) {
 
         return "redirect:/shoppingCartCustomer";
      }
      try {
         cartService.saveOrder(cartInfo);
      } catch (Exception e) {
 
         return "shoppingCartConfirmation";
      }
 
      // Remove Cart from Session.
      Utils.removeCartInSession(request);
 
      // Store last cart.
      Utils.storeLastOrderedCartInSession(request, cartInfo);
 
      return "redirect:/shoppingCartFinalize";
   }
 
   @RequestMapping(value = { "/shoppingCartFinalize" }, method = RequestMethod.GET)
   public String shoppingCartFinalize(HttpServletRequest request, Model model, Authentication authentication, ModelMap map) {

      if (authentication != null) {
         Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
         List<String> roles = new ArrayList<String>();
         for (GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
         }
         if (isUser(roles)) {
            map.addAttribute("header", "header_user");
            map.addAttribute("footer", "footer_user");
         } else if (isAdmin(roles)) {
            map.addAttribute("header", "header_admin");
            map.addAttribute("footer", "footer_admin");
         }
      } else {
         map.addAttribute("header", "header_login");
         map.addAttribute("footer", "footer_login");
      }
 
      CartInfo lastOrderedCart = Utils.getLastOrderedCartInSession(request);
 
      if (lastOrderedCart == null) {
         return "redirect:/shoppingCart";
      }
      model.addAttribute("lastOrderedCart", lastOrderedCart);
      return "shoppingCartFinalize";
   }


//    @RequestMapping(value = { "/bookImage" }, method = RequestMethod.GET)
//    public void productImage(HttpServletRequest request, HttpServletResponse response, Model model,
//          @RequestParam("bookId") Long bookId) throws IOException {
//       Book book = null;
//       if (bookId != null) {
// 		book = this.bookService.findBookByID(bookId);
//       }
//       if (book != null && book.getPicture() != null) {
//          response.setContentType("images/jpeg, images/jpg, images/png, images/gif");
//          response.getOutputStream().write(book.getPicture());
//       }
//       response.getOutputStream().close();
//    }

   private boolean isUser(List<String> roles) {
      if (roles.contains("ROLE_EMPLOYEE")) {
         return true;
      }
      return false;
   }

   private boolean isAdmin(List<String> roles) {
      if (roles.contains("ROLE_ADMIN")) {
         return true;
      }
      return false;
   }
}
