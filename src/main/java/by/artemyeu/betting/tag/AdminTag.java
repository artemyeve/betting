package by.artemyeu.betting.tag;

import by.artemyeu.betting.entity.User;
import by.artemyeu.betting.entity.UserRole;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Created by Acer on 16.05.2017.
 */
public class AdminTag extends TagSupport {
    private static final String USER_ATTR="user";

    /**
     * Do start tag.
     *
     * @return the int
     * @throws JspException the jsp exception
     */
    @Override
    public int doStartTag() throws JspException {
        User user=(User)pageContext.getSession().getAttribute(USER_ATTR);
        if (user!=null) {
            if(user.getRole()== UserRole.ADMIN)
                return EVAL_BODY_INCLUDE;
            else {
                return SKIP_BODY;
            }
        } else {
            return SKIP_BODY;
        }
    }
}
