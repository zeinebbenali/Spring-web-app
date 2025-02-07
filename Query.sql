SELECT 
    CASE 
        WHEN EXTRACT(DOW FROM CURRENT_DATE) = 1 THEN CURRENT_DATE - INTERVAL '3 days'
        WHEN EXTRACT(DOW FROM CURRENT_DATE) = 0 THEN CURRENT_DATE - INTERVAL '2 days'
        ELSE CURRENT_DATE - INTERVAL '1 day'
    END AS previous_workday;
