
package src;
import java.sql.*;
import javax.swing.*;

/**
 *
 * @author kevin
 */
public class FriendEditor extends javax.swing.JFrame {


    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    String Email;
    String nEmail;
    String friendId;
    String userId;
    String friendFID;
    String userFID;
    
    public FriendEditor(String Email) {
        this.Email = Email;
        initComponents();
        conn=connect.connect();
    }
    
   private void checkFriend (){
      String friendEmail;
       String sql = "SELECT Facebook.Email, Facebook.Name FROM Users, Facebook, (SELECT DISTINCT f_userid1 FROM Friends WHERE f_userid2 = (select Users.ID from Users, Facebook where Facebook.Email = '"+nEmail+"' and Users.u_FacebookId = Facebook.FacebookId) OR f_userid1 IN (SELECT f_userid2 FROM friends WHERE f_userid1 = (select Users.ID from Users, Facebook where Facebook.Email = '"+nEmail+"' and Users.u_FacebookId = Facebook.FacebookId)))fID WHERE Users.ID = fID.f_userid1 AND Users.u_FacebookId = Facebook.FacebookId";
       try{
           ps = conn.prepareStatement(sql);
           rs=ps.executeQuery();
           while(rs.next()){
               
           friendEmail = rs.getString("Email"); 
           if (friendEmail.equals(nEmail)){
           JOptionPane.showMessageDialog(null,"Username is already a friend.");
            ps.close();
            rs.close();

           }
           else{
               add_friend(nEmail);
           }
           
           }
           
           
       }
       catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
       }
       finally {
            try{
                rs.close(); 
                ps.close(); 
            }
            catch(Exception e) { } }
    }
   private void checkExists (){
      String friendEmail;
       String sql = "SELECT Facebook.Email, Facebook.Name FROM Users, Facebook, (SELECT DISTINCT f_userid1 FROM Friends WHERE f_userid2 = (select Users.ID from Users, Facebook where Facebook.Email = '"+Email+"' and Users.u_FacebookId = Facebook.FacebookId) OR f_userid1 IN (SELECT f_userid2 FROM friends WHERE f_userid1 = (select Users.ID from Users, Facebook where Facebook.Email = '"+Email+"' and Users.u_FacebookId = Facebook.FacebookId)))fID WHERE Users.ID = fID.f_userid1 AND Users.u_FacebookId = Facebook.FacebookId";
       try{
           ps = conn.prepareStatement(sql);
           rs=ps.executeQuery();
           while(rs.next()){
           friendEmail = rs.getString("Email"); 
           if (friendEmail.equals(nEmail)){
            ps.close();
            rs.close();
            remove_friend(nEmail);
           

           }
           else{
             JOptionPane.showMessageDialog(null,"That is not a valid username to remove.");  
           }
           
           }
           
           
       }
       catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
       }
       finally {
            try{
                rs.close(); 
                ps.close(); 
            }
            catch(Exception e) { } }
    }
   private void remove_friend(String nEmail)
   {
       try
       {
           String sql = "select facebookId from facebook where email = ?";
           ps = conn.prepareStatement(sql);
           ps.setString(1, nEmail);
           rs=ps.executeQuery();
           friendFID = rs.getString("FacebookId");
           rs.close();
           ps.close();
           
           sql = "select facebookId from facebook where email = ?";
           ps = conn.prepareStatement(sql);
           ps.setString(1, Email);
           rs=ps.executeQuery();
           userFID = rs.getString("FacebookId");
           rs.close();
           ps.close();
           
           sql = "select ID from users, facebook where email = ? and facebookId =? and facebookId = u_facebookId ";
           ps = conn.prepareStatement(sql);
           ps.setString(1, Email);
           ps.setString(2,userFID);
           rs=ps.executeQuery();
           userId = rs.getString("ID");
           rs.close();
           ps.close();
           
           sql = "select ID from users, facebook where email = ? and facebookId =? and facebookId = u_facebookId ";
           ps = conn.prepareStatement(sql);
           ps.setString(1, nEmail);
           ps.setString(2,friendFID);
           rs=ps.executeQuery();
           friendId = rs.getString("ID");
           rs.close();
           ps.close();
           
           sql = "delete from friends where (f_userid1 = ? and f_userid2 = ?) OR (f_userid1 = ? and f_userid2 = ?)";
           ps = conn.prepareStatement(sql);
           ps.setString(1, userId);
           ps.setString(2,friendId);
           ps.setString(3,friendId);
           ps.setString(4,userId);
           ps.execute();
           ps.close();
           rs.close();
           JOptionPane.showMessageDialog(null,"User removed from friends list."); 
       }
        catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
       }
       finally {
            try{
                rs.close(); 
                ps.close(); 
            }
            catch(Exception e) { } }
   }
   private void add_friend(String nEmail)
   {
       try
       {
           String sql = "select facebookId from facebook where email = ?";
           ps = conn.prepareStatement(sql);
           ps.setString(1, nEmail);
           rs=ps.executeQuery();
           friendFID = rs.getString("FacebookId");
           rs.close();
           ps.close();
           
           sql = "select facebookId from facebook where email = ?";
           ps = conn.prepareStatement(sql);
           ps.setString(1, Email);
           rs=ps.executeQuery();
           userFID = rs.getString("FacebookId");
           rs.close();
           ps.close();
           
           sql = "select ID from users, facebook where email = ? and facebookId =? and facebookId = u_facebookId ";
           ps = conn.prepareStatement(sql);
           ps.setString(1, Email);
           ps.setString(2,userFID);
           rs=ps.executeQuery();
           userId = rs.getString("ID");
           rs.close();
           ps.close();
           
           sql = "select ID from users, facebook where email = ? and facebookId =? and facebookId = u_facebookId ";
           ps = conn.prepareStatement(sql);
           ps.setString(1, nEmail);
           ps.setString(2,friendFID);
           rs=ps.executeQuery();
           friendId = rs.getString("ID");
           rs.close();
           ps.close();
           
           sql = "insert into friends values(?,?)";
           ps = conn.prepareStatement(sql);
           ps.setString(1, userId);
           ps.setString(2,friendId);
           ps.execute();
           ps.close();
           rs.close();
           
           JOptionPane.showMessageDialog(null,"New friend added.");
       }
        catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
       }
       finally {
            try{
                rs.close(); 
                ps.close(); 
            }
            catch(Exception e) { } }   }
    
   
   private void checkUser()
   {
    String sql ="select * from Facebook WHERE Email = ?;";
       try{
          nEmail = email_text.getText();
           ps = conn.prepareStatement(sql);
           ps.setString(1,nEmail);
           rs=ps.executeQuery();
           if(rs.next()){
               ps.close();
               rs.close();
           }
           else{
               JOptionPane.showMessageDialog(null,"User doesn't exist.");
           }
       }
       catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
       }
       finally {
            try{
                rs.close(); 
                ps.close(); 
            }
            catch(Exception e) { } }
      
   }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        add_button = new javax.swing.JButton();
        remove_button = new javax.swing.JButton();
        name_label = new javax.swing.JLabel();
        email_text = new javax.swing.JTextField();
        back_button = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 3, 24)); // NOI18N
        jLabel1.setText("Friend Editor");

        add_button.setText("Add");
        add_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_buttonActionPerformed(evt);
            }
        });

        remove_button.setText("Remove");
        remove_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remove_buttonActionPerformed(evt);
            }
        });

        name_label.setText("Email:");

        back_button.setText("<< Back");
        back_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(back_button, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(name_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(email_text, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(remove_button, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                            .addComponent(add_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(name_label)
                    .addComponent(email_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_button, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(remove_button)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                .addComponent(back_button, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void add_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_buttonActionPerformed
        checkUser();
        checkFriend();
        try{
                rs.close(); 
                ps.close(); 
            }
            catch(Exception e) { }
    }//GEN-LAST:event_add_buttonActionPerformed

    private void remove_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remove_buttonActionPerformed
          nEmail = email_text.getText();
          checkExists();
          try{
                rs.close(); 
                ps.close(); 
            }
            catch(Exception e) { }
    }//GEN-LAST:event_remove_buttonActionPerformed

    private void back_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back_buttonActionPerformed
        try{
                rs.close(); 
                ps.close(); 
            }
            catch(Exception e) { }       
            user_info x = new user_info(Email);
               x.setVisible(true); 
               this.dispose();
    }//GEN-LAST:event_back_buttonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FriendEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FriendEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FriendEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FriendEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add_button;
    private javax.swing.JButton back_button;
    private javax.swing.JTextField email_text;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel name_label;
    private javax.swing.JButton remove_button;
    // End of variables declaration//GEN-END:variables
}
