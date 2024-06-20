package Utils;

/**
 *
 * @author Le Tan Kim
 */
public class Pagination {

    public String generatePagination(int currentPage, int totalPages, String typePage, String... key) {
        String keyPath = String.join("", key);
        keyPath = keyPath.equals("") ? "" : keyPath;
        if(!keyPath.startsWith("?type") && !keyPath.equals("")) {
            keyPath = "?keyword=" + keyPath;
        }
        int maxVisiblePages = 5; 
        int pagesToShowOnEachSide = maxVisiblePages / 2;
        StringBuilder pagination = new StringBuilder();

        pagination.append("<div class=\"store-filter clearfix\">");
        pagination.append("<span class=\"store-qty\">Showing ").append((currentPage - 1) * 20 + 1).append("-").append(Math.min(currentPage * 20, totalPages * 20)).append(" products</span>");
        pagination.append("<ul class=\"store-pagination\">");

        if (currentPage > 1) {
            pagination.append("<li><a href=\"").append(typePage).append("/page-").append(currentPage - 1).append(keyPath).append("\"><i class=\"fa fa-angle-left\"></i></a></li>");
        }

        for (int page = 1; page <= totalPages; page++) {
            if (page == 1 || page == totalPages || (page >= currentPage - pagesToShowOnEachSide && page <= currentPage + pagesToShowOnEachSide)) {
                if (page == currentPage) {
                    pagination.append("<li class=\"active\">").append(page).append("</li>");
                } else {
                    pagination.append("<li><a href=\"").append(typePage).append("/page-").append(page).append(keyPath).append("\">").append(page).append("</a></li>");
                }
            } else if (pagination.lastIndexOf("...") != pagination.length() - 3) {
                pagination.append("<li><span>...</span></li>");
            }
        }
        if (currentPage < totalPages) {
            pagination.append("<li><a href=\"").append(typePage).append("/page-").append(currentPage + 1).append(keyPath).append("\"><i class=\"fa fa-angle-right\"></i></a></li>");
        }

        pagination.append("</ul>");
        pagination.append("</div>");

        return pagination.toString();
    }

}
